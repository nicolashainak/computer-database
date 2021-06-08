package com.excilys.cdb.persistance;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.binding.dto.DtoComputerDbService;
import com.excilys.cdb.binding.mapper.MapperComputer;
import com.excilys.cdb.binding.mapper.MapperDtoComputerDbService;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;

@Repository
public class DaoComputer {
	Logger logger = LoggerFactory.getLogger(DaoComputer.class);

	private DaoComputer() {
	}

	private static DaoComputer instance = new DaoComputer();
	private final static String RQTSELECTCOMPUTERALL = "select computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id,company.name from computer  left join company on computer.company_id = company.id limit ? offset ? ;";
	private final static String RQTINSERT = "INSERT INTO computer( name,introduced,discontinued,company_id) VALUES(?,?,?,?);";
	private final static String RQTSEARCH = "select computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id,company.name from computer  left join company on computer.company_id = company.id where computer.id = ? ; ";
	private final static String RQTUPDATE = "update computer set name = ?,introduced = ? , discontinued = ?, company_id = ? where id = ?";
	private final static String RQTDELETEBYID = "delete from computer where id=?";
	private final static String RQTNBCOMPUTER = "SELECT COUNT(*) FROM computer ;";
	private final static String RQTORDERBY = "select computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id,company.name from computer  left join company on computer.company_id = company.id ORDER BY ";
	private final static String RQTSEARCHWITH = "select computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id,company.name from computer  left join company on computer.company_id = company.id  WHERE computer.name LIKE ? OR company.name LIKE ? ORDER BY ";
	private static final String RQTNOMBREELEMENTSSEARCH = "SELECT COUNT(computer.id)FROM computer LEFT JOIN company on company.id = computer.company_id WHERE computer.name LIKE ? OR company.name LIKE ? ";
	public static DaoComputer getInstance() {
		return instance;
	}

	public List<Computer> getListComputer(Page page) {
		List<Computer> listComputer = new ArrayList<Computer>();

		try (Connection connection = CdbConnection.getInstance().getConnection();) {// connection dans les parenthèses
			PreparedStatement preparedStatement = connection.prepareStatement(RQTSELECTCOMPUTERALL);

			preparedStatement.setInt(1, page.getNbComputerParPage());

			preparedStatement.setInt(2, page.getNbComputerParPage() * (page.getNumPage() - 1));
			ResultSet resultSet = preparedStatement.executeQuery();
	
			listComputer = MapperComputer.writeResultSet(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listComputer;
	}
	public List<Computer> getListComputer(Page page, String search,String collonne,Boolean reverse) {
		List<Computer> listComputer = new ArrayList<Computer>();
		try (Connection connection = CdbConnection.getInstance().getConnection();) {
			PreparedStatement preparedStatement;
			if (reverse) {
				preparedStatement = connection.prepareStatement(RQTSEARCHWITH + collonne + " DESC limit ? offset ?");
			} else {
				preparedStatement = connection.prepareStatement(RQTSEARCHWITH + collonne + " limit ? offset ?");
			}
			preparedStatement.setString(1,"%"+search+"%");
			preparedStatement.setString(2,"%"+search+"%");
			preparedStatement.setInt(3, page.getNbComputerParPage());
			preparedStatement.setInt(4, page.getNbComputerParPage() * (page.getNumPage() - 1));
			ResultSet resultSet = preparedStatement.executeQuery();
			listComputer = MapperComputer.writeResultSet(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listComputer;
	}
	public List<Computer> searchComputerWith(Page page, String search,String collonne) {
		List<Computer> listComputer = new ArrayList<Computer>();
		try (Connection connection = CdbConnection.getInstance().getConnection();) {
			PreparedStatement preparedStatement = connection.prepareStatement(RQTSEARCHWITH+collonne+" limit ? offset ?");
			preparedStatement.setString(1,"%"+search+"%");
			preparedStatement.setString(2,"%"+search+"%");
			preparedStatement.setInt(3, page.getNbComputerParPage());
			preparedStatement.setInt(4, page.getNbComputerParPage() * (page.getNumPage() - 1));
			ResultSet resultSet = preparedStatement.executeQuery();
			listComputer = MapperComputer.writeResultSet(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listComputer;
	}

	public int nbElementSearch(String search) {
		try (Connection connection = CdbConnection.getInstance().getConnection();) {

			PreparedStatement preparedStatement = connection.prepareStatement(RQTNOMBREELEMENTSSEARCH);
			preparedStatement.setString(1, "%"+search+"%");
			preparedStatement.setString(2, "%"+search+"%");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int nbSearch = Integer.parseInt(resultSet.getString(1));
				return nbSearch;
			}

			
		}catch (SQLException e){
			logger.error("MySQL error : " + e);
			return 0;
		}
		return 0; 
	}
	public void newComputer(Computer computer) {
		DtoComputerDbService c = MapperDtoComputerDbService.mapperDtoToDbService(computer);
		try (Connection connection = CdbConnection.getInstance().getConnection();) {

			PreparedStatement preparedStatement = connection.prepareStatement(RQTINSERT);

			preparedStatement.setString(1, c.getName());

			if (c.getIntroduced() != null) {
				preparedStatement.setDate(2, c.getIntroduced());
			} else {
				preparedStatement.setNull(2, 0);
			}
			if (c.getDiscontinued() != null) {
				preparedStatement.setDate(3, c.getDiscontinued());
			} else {
				preparedStatement.setNull(3, 0);
			}
			if (c.getCompany().getId() == 0) {
				preparedStatement.setNull(4, 0);
			} else {
				preparedStatement.setInt(4, c.getCompany().getId());
			}
			preparedStatement.executeUpdate();
		} catch (SQLException e) {

			logger.error("MySQL error : " + e);
		}

	}

	public List<Computer> orderBy(Page page, String collonne, Boolean reverse) {
		List<Computer> listComputer = new ArrayList<Computer>();
		try (Connection connection = CdbConnection.getInstance().getConnection();) {
			PreparedStatement preparedStatement;
			if (reverse) {
				preparedStatement = connection.prepareStatement(RQTORDERBY + collonne + " DESC limit ? offset ?");
			} else {
				preparedStatement = connection.prepareStatement(RQTORDERBY + collonne + " limit ? offset ?");
			}
			preparedStatement.setInt(1, page.getNbComputerParPage());
			preparedStatement.setInt(2, page.getNbComputerParPage() * (page.getNumPage() - 1));
			ResultSet resultSet = preparedStatement.executeQuery();
			listComputer = MapperComputer.writeResultSet(resultSet);
		} catch (SQLException e) {

			logger.error("MySQL error : " + e);
		}
		return listComputer;
	}

	/////// Changer en return Computer --> ajouter methode mapper pour 1 seul ordi
	/////// cf company
	public Computer searchComputer(int idComputer) {
		Computer computer = new Computer();
		
		try (Connection connection = CdbConnection.getInstance().getConnection();) {
			
			PreparedStatement preparedStatement = connection.prepareStatement(RQTSEARCH);
			preparedStatement.setInt(1, idComputer);
			ResultSet resultSet = preparedStatement.executeQuery();
			computer = MapperComputer.writeAResultSet(resultSet);

		} catch (SQLException e) {
			logger.error("MySQL error : " + e);
		}
		return computer;
	}

	public void updateComputer(int idComputer, Computer computer) {
		DtoComputerDbService c = MapperDtoComputerDbService.mapperDtoToDbService(computer);
		try (Connection connection = CdbConnection.getInstance().getConnection();) {

			PreparedStatement preparedStatement = connection.prepareStatement(RQTUPDATE);
			
			preparedStatement.setString(1, c.getName());
			if (c.getIntroduced() != null) {
				preparedStatement.setDate(2, c.getIntroduced());
			} else {
				preparedStatement.setNull(2, 0);
			}
			if (c.getDiscontinued() != null) {
				preparedStatement.setDate(3, c.getDiscontinued());
			} else {
				preparedStatement.setNull(3, 0);
			}
			if (c.getCompany().getId() == 0) {
				preparedStatement.setNull(4, 0);
			} else {
				preparedStatement.setInt(4, c.getCompany().getId());
			}
			preparedStatement.setInt(5, idComputer);
			preparedStatement.executeUpdate();
			// Database.close();
		} catch (SQLException e) {
			logger.error("MySQL error : " + e);
		}
	}

	public void deleteComputer(int idComputer) {
		try (Connection connection = CdbConnection.getInstance().getConnection();) {
			System.out.println("delete id " + idComputer);
			PreparedStatement preparedStatement = connection.prepareStatement(RQTDELETEBYID);
			preparedStatement.setInt(1, idComputer);
			preparedStatement.executeUpdate();
			// Database.close();
		} catch (SQLException e) {
			logger.error("MySQL error : " + e);
		}
	}

	public int nbComputer() {

		try (Connection connection = CdbConnection.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(RQTNBCOMPUTER);) {

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int id = Integer.parseInt(resultSet.getString(1));
				return id;
			}

		} catch (SQLException e) {

			logger.error("MySQL error : " + e);
		}

		return 0;
	}

}
