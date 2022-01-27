import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import org.bson.Document
import org.json.JSONTokener
import java.net.URL
import java.util.logging.Level
import java.util.logging.LogManager

fun main() {
    LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE)
    val con = MongoClient(MongoClientURI("mongodb://ad:ieselcaminas@89.36.214.106/?authSource=test"))
    val bd = con.getDatabase("test")

    val ordenar = Document()
    ordenar.put("precio", -1)

    val llibres = bd.getCollection("libro").find().sort(ordenar)

    for (llibre in llibres)
        System.out.println(llibre.get("titulo").toString() + " --> " + llibre.get("precio"))

    con.close()
}

