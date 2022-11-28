package ni.edu.uca.taskitty.data

import ni.edu.uca.taskitty.model.DYK
import ni.edu.uca.taskitty.R

class DataSourceDYK {

    fun loadDYK() : List <DYK>{
        return listOf(
            DYK(R.drawable.img_ani_zebra,
            "Las cebras se comunican con los de su propia especie a través de expresiones faciales y sonidos. Sus labios son bastante flexibles, lo que les permite formar expresiones faciales complejas. Sus modos visuales de comunicación también incluyen la posición de las orejas, la cabeza y la cola.",
            R.drawable.bg_jungle),
            DYK(R.drawable.img_ani_elephant,
            "Según varios descubrimientos científicos relativamente recientes, los elefantes son capaces de producir ruidos infrasónicos para comunicarse con otros de su especie. Estos sonidos resultan imperceptibles para el oído humano.",
            R.drawable.bg_jungle),
            DYK(R.drawable.img_ani_hippopotamus,
            "También llamados hipocampos, esta especie cuenta con la particularidad de que son los machos quienes traen las crías al mundo. Es la hembra quien deposita los huevos en una bolsa en el interior del macho, así, él genera una especie de placenta y después alimenta a sus hijos.",
            R.drawable.bg_ocean),
            DYK(R.drawable.img_ani_tiger,
            "Los tigres pueden parecer versiones gigantes de nuestros gatos domésticos pero, a diferencia de la mayoría de los gatos domésticos, aman el agua y disfrutan nadar. Incluso cazan en el agua y pueden nadar muy lejos. ¡Mamá tigres dan clases de natación a sus cachorros!",
            R.drawable.bg_jungle),
            DYK(R.drawable.img_ani_giraffe,
            "Si bien pueden comer mucho, las jirafas no beben mucha agua. Esto se debe a que obtienen la mayor parte del agua de sus comidas con hojas y solo necesitan beber una vez cada pocos días.",
            R.drawable.bg_jungle),
            DYK(R.drawable.img_ani_bear,
            "Los osos son animales extraordinariamente inteligentes. Tienen habilidades de navegación muy superiores a las de los humanos, excelente memoria, gran proporción cerebro-cuerpo y usan herramientas en varios contextos, desde el juego hasta la caza.",
            R.drawable.bg_forest),
            DYK(R.drawable.img_ani_racoon,
            "Los mapaches son conocidos por sus máscaras faciales oscuras parecidas a las de los bandidos. Una teoría es que las marcas oscuras distintivas ayudan a desviar el resplandor del sol y también pueden mejorar la visión nocturna.",
            R.drawable.bg_forest),
        )
    }

    fun getRndDYK(): DYK {
        val listDYK = loadDYK()
        return listDYK[(0..6).random()]
    }
}