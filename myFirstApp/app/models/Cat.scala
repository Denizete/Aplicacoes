package models

import play.api.db.slick.Config.driver.simple._

case class Cat(name: String, gender: String, color: String){

  object Cats extends Table[Cat]("CAT") {
    def name = column[String]("name", O.PrimaryKey)
    def gender = column[String]("gender", O.NotNull)
    def color = column[String]("color", O.NotNull)
    
    def * = name ~ gender ~ color <> (Cat, Cat.unapply _)
  
    // auto increment handler
    def autoInc = * returning name
    
    def insert(cat: Cat) = DB.withSession { implicit session =>
    autoInc.insert(cat)
    
    def update(name: String, cat: Cat) {
	    DB.withSession { implicit session =>
	      Cats.where(_.name === name).update(cat)
	    }
    }
    
    def delete(name: String) {
	    DB.withSession { implicit session =>
	       Cats.where(_.name === name).delete
	    }
    }
    
    
  }
  
}