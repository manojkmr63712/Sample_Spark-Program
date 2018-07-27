import java.sql.DriverManager
import java.sql.Connection

case class MyData(ID: Int, Name: String){
  override def toString(): String = {

  }
}

object MysqlData {
  def main(args: Array[String]): Unit = {
    val driver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://localhost/manoj"
    val username = "root"
    val password = "123"

    Class.forName(driver);
    val connection = DriverManager.getConnection(url,username,password)
    val statement = connection.createStatement()
    val resultSet = statement.executeQuery("select * from sample")
    while(resultSet.next()){

      val id = resultSet.getString("id")
      val name = resultSet.getString("name")
      println("id =" + id + ", Name ="+ name)
    }
  }

}
