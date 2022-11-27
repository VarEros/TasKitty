package ni.edu.uca.taskitty.data

import ni.edu.uca.taskitty.DYK
import ni.edu.uca.taskitty.R

class DataSourceDYK {

    fun loadDYK() : List <DYK>{
        return listOf(
            DYK(R.drawable.ant,
            "Las hormigas son insectos curiosos. Hacen guerras, pueden capturar a esclavos, siempre viven en comunidad y se organizan de una forma pasmosa para conseguir su comida y construir sus hogares. Sin duda, son imparables. Toda esta actividad quizá se explica porque son seres incapaces de dormir ni un solo segundo.",
            R.drawable.meadow),
            DYK(R.drawable.elefant,
            "Según varios descubrimientos científicos relativamente recientes, los elefantes son capaces de producir ruidos infrasónicos para comunicarse con otros de su especie. Estos sonidos resultan imperceptibles para el oído humano. Algo similar ocurre con los lobos. El jefe de una manada puede llamar a otros lobos que estén situados a más de 20 kilómetros y hacerse oír.",
            R.drawable.selva),
            DYK(R.drawable.hipocampo,
            "También llamados hipocampos, esta especie cuenta con la particularidad de que son los machos quienes traen las crías al mundo. Es la hembra quien deposita los huevos en una bolsa en el interior del macho, así, él genera una especie de placenta y después alimenta a sus hijos.",
            R.drawable.ocean_background),
            DYK(R.drawable.abejas,
            "Las abejas son insectos himenópteros, que además de proporcionarnos la miel, son los polinizadores más importantes del planeta, por delante de pájaros y murciélagos. Una cuarta parte de las especies vegetales que florecen dependen de ellas.",
            R.drawable.meadow),
            DYK(R.drawable.productos_ecologicos,
            "Los productos ecológicos generan menos contaminación ambiental ya que no se utilizan fertilizantes. Te recomendamos realizar un huerto en casa, de esta manera disfrutarás alimentos frescos y llenos de nutrientes. ",
            R.drawable.meadow),
            DYK(R.drawable.arboles,
            "Los árboles son esenciales para el mundo, producen oxígeno, reducen el dióxido de carbono, absorben gases contaminantes, regulan la temperatura, entre otros beneficios. Así que ya sabes, planta un árbol y contribuye al cuidado del medio ambiente. ",
            R.drawable.selva),
            DYK(R.drawable.agua,
            "El agua es un recurso imprescindible y escaso que debemos usar con responsabilidad. Cerrar el grifo mientras te lavas los dientes, bañarte en 5 minutos, juntar el agua de la regadera mientras te bañas, son pequeñas acciones que ayudan a ahorrarla.",
            R.drawable.ocean_background),
            DYK(R.drawable.murcielago,
            "Los murciélagos de las áreas urbanas pueden consumir casi 14.000 kilos de insectos en una sola noche. De esta manera nos ayudan a librarnos de innumerables plagas nocivas. Además, en sus desplazamientos llevan consigo todo tipo de semillas.",
            R.drawable.cave),
            DYK(R.drawable.primate,
            "La importancia de los primates no reside únicamente en el parentesco, dado que compartimos el 90 % de nuestros genes. Dependemos de su hábitat porque los bosques son importantes sumideros de carbono que liberan oxígeno a través de la fotosíntesis, y por su función de evapotranspiración que influye en las precipitaciones.",
            R.drawable.selva),
            DYK(R.drawable.animales_salvajes,
            "Los animales carroñeros mantienen el ecosistema limpio de toda materia en descomposición. Eliminan restos orgánicos, evitando la superpoblación de microorganismos perjudiciales para el entorno. Los restos dejados por los carroñeros son después usados por los descomponedores. Los animales carroñeros más conocidos son los buitres y las hienas.",
            R.drawable.ecosystem)
        )
    }

    fun getRndsDYK(): DYK{
        val listDYK = loadDYK()
        return listDYK[(0..10).random()]
    }
}