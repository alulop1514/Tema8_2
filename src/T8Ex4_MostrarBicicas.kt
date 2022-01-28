import com.mongodb.MongoClient
import org.bson.Document
import java.util.logging.Level
import java.util.logging.LogManager

fun main() {
    LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE)
    // Instruccions per a llegir de la pàgina de Bicicas i col·locar en arrel
    val con = MongoClient("localhost", 27017)
    val bd = con.getDatabase("test")

    val ordenar = Document()
    ordenar["id"] = 1
    val estacions = bd.getCollection("bicicas").find().sort(ordenar)
    for (estacio in estacions)
        println("${estacio["id"]}.- ${estacio["punto"]} (${estacio["ocupados"]}/${estacio["puestos"]})")

    con.close()
}