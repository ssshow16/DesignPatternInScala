package facade.pagemaker

import java.util.Properties
import java.io._

object Database{
	def properties(dbname:String) : Properties = {
		val filename = s"${dbname}.txt"
		val prop = new Properties()
		try{
			prop.load(new FileInputStream(filename))
		}catch{
			case e:IOException => println(e)
		}
		prop
	}
}

class HtmlWriter(writer:Writer){
	def title(title:String) = {
		writer.write("<html>")
		writer.write("<head>")
		writer.write(s"<title>$title</title>")
		writer.write("</head>")
		writer.write("<body>\n")
		writer.write(s"<h1>$title</h1>")
	}

	def paragraph(msg:String){
		writer.write(s"<p>$msg</p>")
	}

	def mailto(mailaddr:String, username:String) {
		writer.write(s"<mailto:$mailaddr, $username")
	}

	def close(){
		writer.write("</body>")
		writer.write("</html>")
		writer.close()
	}
}

object PageMaker{
	def makeWelcomePage(mailaddr:String, filename:String){

		val mailprop = Database.properties("maildata")
		val username = mailprop.getProperty(mailaddr)
		val writer = new HtmlWriter(new FileWriter(filename))
		writer.title(s"Welcome to ${username}'s page!")
		writer.paragraph(s"${username}의 페이지에 오신걸 환영합니다.")
		writer.paragraph(s"메일이 기다리겠습니다.")
		writer.mailto(mailaddr,username)
		writer.close()

		println(s"${filename} is created for ${mailaddr} ($username)")
	}
}



