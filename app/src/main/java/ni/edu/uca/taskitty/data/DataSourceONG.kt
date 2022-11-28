package ni.edu.uca.taskitty.data
import ni.edu.uca.taskitty.R
import ni.edu.uca.taskitty.model.ONG

class DataSourceONG {
    companion object
    {
        fun loadONG(): List<ONG> {
            return listOf(
                ONG(
                    R.drawable.logo_rescatandohuellas,
                    "Rescatando Huellas Nicaragua.",
                    "Rescatando Huellas se trata de una organización enfocada en la protección y bienestar de animales que hayan sido abandonados o maltratados. ",
                    "why yes?"
                ),
                ONG(
                    R.drawable.logo_refugiochachocente,
                    "Refugio de vida silvestre.",
                    "Se encuentra en la región del Pacífico Sur entre los departamentos de Carazo y Rivas. Tiene una superficie de 29,604 hectáreas distribuyéndose de la siguiente manera: un 90% en el municipio de Santa Teresa.",
                    "why  not?"
                ),
                ONG(
                    R.drawable.logo_casahogarlucito,
                    "Casa hogar Lucito.",
                    "Son un conjunto de jóvenes dedicados a la protección y promoción de los derechos de los animales, además de la preservación de la flora y fauna de Nicaragua. La organización con la ayuda de su personal y de personas externas, organizan recaudaciones de fondos, eventos de desarrollo comunitario y actividades las cuales benefician a los animales y al medio ambiente. ",
                    "why  not?"
                ),
                ONG(
                    R.drawable.logo_mininosuca,
                    "Mininos UCA.",
                    "Organización de la universidad centroamericana UCA la cual se enfoca en el cuido de los gatitos que se encuentran dentro de la institución. A los gatos se les da alimento y cambio de agua, al mismo tiempo por medio en caso que un gato lo necesite se le puede realizar una operación.",
                    "why  not?"
                ),
                ONG(
                    R.drawable.logo_unanmanagua,
                    "Zoocriadero UNAN - Managua.",
                    "Tiene el objetivo de proteger y conservar la especie de iguana verde. El zoocriadero se fundo con el objetivo de investigación a comunidades rurales, al mismo tiempo con el objetivo de la educación ambiental de jovenes en el cuido de iguanas verdes. ",
                    "why  not?"
                ),
                ONG(
                    R.drawable.logo_zoologiconacional,
                    "Zoológico Nacional De Nicaragua.",
                    "El Zoológico nacional es el más grande de Nicaragua, exhibe más de seiscientas especies de la fauna nacional, la mayoriía silvestres y algunas domésticas, adempas de varios ejemplares exóticos oriundos de sitios lejanos como África y Asia. Algunos animales que se pueden observar se encuentran en peligro de extinción. ",
                    "why  not?"
                ),
                ONG(
                    R.drawable.logo_estoyaquinicaragua,
                    "Estoy Aquí Nicaragua.",
                    "Se trata de un proyecto que tiene el objetivo de hacer que los animales callejeros sean notados en el país, al mismo tiempo fomentar a la responsabilidad de lo que significa tener una mascota.",
                    "why  not?"
                )
            )
        }
    }
}

