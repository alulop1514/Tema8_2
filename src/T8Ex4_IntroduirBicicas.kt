import com.mongodb.MongoClient
import org.bson.Document
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.net.URL
import java.util.logging.Level
import java.util.logging.LogManager

fun main() {
    LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE)
    // Instruccions per a llegir de la pàgina de Bicicas i col·locar en arrel
    val bicicas = URL("http://gestiona.bicicas.es/apps/apps.php");
    val arrel = (JSONTokener(bicicas.openConnection().getInputStream()).nextValue() as JSONArray).get(0) as JSONObject
    val estacions = arrel.getJSONArray("ocupacion")
    val con = MongoClient("localhost", 27017)
    val bd = con.getDatabase("test")

    for (i in 0 until estacions.length()) {
        val estacion = estacions.get(i) as JSONObject
        val doc = Document()
        doc.put("id", "${estacion.get("id")}")
        doc.put("punto", "${estacion.get("punto")}")
        doc.put("ocupados", "${estacion.get("ocupados")}")
        doc.put("puestos", "${estacion.get("puestos")}")
        bd.getCollection("bicicas").insertOne(doc)
    }


    con.close()
}