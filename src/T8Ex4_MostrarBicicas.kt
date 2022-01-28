import com.mongodb.MongoClient
import org.bson.Document
import java.util.logging.Level
import java.util.logging.LogManager

fun main() {
    LogManager.getLogManager().getLogger("").level = Level.SEVERE
    // Conexio a la base de dades
    val con = MongoClient("localhost", 27017)
    val bd = con.getDatabase("test")
    // Agarrem les estaciones y les ordenem
    val estacions = bd.getCollection("bicicas").find()
    for (estacio in estacions)
        println("${estacio["id"]}.- ${estacio["punto"]} (${estacio["ocupados"]}/${estacio["puestos"]})")
    // Tanquem la conexio
    con.close()
}