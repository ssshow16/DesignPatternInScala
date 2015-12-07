package facade

import facade.pagemaker.PageMaker

object FacadeClient{

	def callFacade(){
		PageMaker.makeWelcomePage("a1@test.com","welcome.html")
	}

}