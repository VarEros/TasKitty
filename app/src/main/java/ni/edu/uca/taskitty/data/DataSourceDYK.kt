package ni.edu.uca.taskitty.data

import ni.edu.uca.taskitty.DYK
import ni.edu.uca.taskitty.R

class DataSourceDYK {

    fun loadDYK() : List <DYK>{
        return listOf(
            DYK(icon = R.drawable.flower_icon,
            descripcion = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"),
            DYK(R.drawable.ic_taskitty_icon,
            "Las hormigas son insectos curiosos. Hacen guerras, pueden capturar a esclavos, siempre viven en comunidad y se organizan de una forma pasmosa para conseguir su comida y construir sus hogares. Sin duda, son imparables. Toda esta actividad quizá se explica porque son seres incapaces de dormir ni un solo segundo."),
            DYK(R.drawable.teamf_icon,
            "Según varios descubrimientos científicos relativamente recientes, los elefantes son capaces de producir ruidos infrasónicos para comunicarse con otros de su especie. Estos sonidos resultan imperceptibles para el oído humano. Algo similar ocurre con los lobos. El jefe de una manada puede llamar a otros lobos que estén situados a más de 20 kilómetros y hacerse oír."),
            DYK(R.drawable.ic_icon_ears,
            "También llamados hipocampos, esta especie cuenta con la particularidad de que son los machos quienes traen las crías al mundo. Es la hembra quien deposita los huevos en una bolsa en el interior del macho, así, él genera una especie de placenta y después alimenta a sus hijos."),
            DYK(R.drawable.ic_warning,
            "Es el animal que tiene la mayor actividad sexual del mundo, nunca se cansa hablando metafóricamente. Puede copular unas cien veces al día, y, a veces, lo hace con la misma hembra. Uno de los datos curiosos de animales como los leones y las leonas es que pueden aparearse cada 10 y 20 minutos al día de 5 a 7 días. Aun así, cabe destacar que las leones pueden tardar de 6 a 9 ciclos en quedarse embarazadas."),
            DYK(R.drawable.david,
            "Los productos ecológicos generan menos contaminación ambiental ya que no se utilizan fertilizantes. Te recomendamos realizar un huerto en casa, de esta manera disfrutarás alimentos frescos y llenos de nutrientes. "),
            DYK(R.drawable.equipo,
            "Los árboles son esenciales para el mundo, producen oxígeno, reducen el dióxido de carbono, absorben gases contaminantes, regulan la temperatura, entre otros beneficios. Así que ya sabes, planta un árbol y contribuye al cuidado del medio ambiente. "),
            DYK(R.drawable.ic_star,
            "El agua es un recurso imprescindible y escaso que debemos usar con responsabilidad. Cerrar el grifo mientras te lavas los dientes, bañarte en 5 minutos, juntar el agua de la regadera mientras te bañas, son pequeñas acciones que ayudan a ahorrarla.")
        )
    }
}