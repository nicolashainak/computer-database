/**
 * 
 */

 $(document).submit (function (event) {
	console.log("im in js ");
      var nbCompany=43;
    if( document.formAddComputer.name.value == "" ) {
       alert( "Please provide the computer name!" );
		document.formAddComputer.Name.focus() ;
       return false;
    }
    var regex = /^(19[7-9][0-9]|20[0-2][0-9]|203[0-8])\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])$/
    if( document.formAddComputer.introduced.value != "" &&  !regex.test(document.formAddComputer.introduced.value) ) {
       alert( "The date format is not valid : yyyy-mm-dd" );
       document.formAddComputer.introduced.focus() ;
       return false;
    }
    if( document.formAddComputer.discontinued.value != "" &&  !regex.test(document.formAddComputer.discontinued.value) ) {
        alert( "The date format is not valid : yyyy-mm-dd" );
        document.formAddComputer.discontinued.focus() ;
       return false;
    }
    if( document.formAddComputer.company.value < 0 ||  document.formAddComputer.company.value > nbCompany ) {
       alert( "Please choose -- for no company or a company in the list !" );
        document.formAddComputer.company.focus() ;

       return false;
    }
    return( true );
 })