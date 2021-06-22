
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.excilys.cdb.cli.Cli;
import com.excilys.cdb.config.ConfigurationUi;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.service.MyService;


/**
 * Hello world!
 *
 */
public class App 
{
	
	Cli cli;
	public App(Cli cli) {
		this.cli=cli;
	}
	public static void main(String[] args)  {
		ApplicationContext context=new AnnotationConfigApplicationContext(ConfigurationUi.class);
		MyService service =context.getBean(MyService.class);
		System.out.println("company id = 1");
		System.out.println(service.getCompanyById(1));
		Page page =new Page();
		System.out.println(service.getNbComputerTotal(page));
		System.out.println(service.nbComputerSearch("apple"));
		//System.out.println(service.getListComputer(page,"apple","computer.name",true));
		
		Sort currentSort = 	Sort.by(Order.asc("name"));

		Pageable currentPageable = PageRequest.of(page.getNumPage()-1, page.getNbComputerParPage(), currentSort);
		System.out.println(service.getListComputer(" ", currentPageable));
		System.out.println();
		//		Cli cli = context.getBean(Cli.class);
//		cli.boucle();
	}
}
	
	


