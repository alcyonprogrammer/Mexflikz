# MexFlikz
Cartelera test de Peliculas y Series

<p>Consumo de datos de peliculas/series para probar diversos componentes y arquitecturas (MVVM)</p>


## Web Services
 <li> REST Services https://api.themoviedb.org/3/ para el listado de peliculas y Series</li>

## Características.
<li>Muestra peliculas </li>
<li>Muestra Series </li>
<li>Identifica mediante diferentes tamaños de imagenes las mas vistas</li>
<li>Las secciones pueden ser personalizadas facilmente incluyendo la categoria general a mostrar como Top, Latest, etc.</li>
<li>Muestra un detalle con imagen si no encontro un trailer oficial o un video con youtube API</li>
<li>Cache de imagenes </li>

## Configuración
La Clase  Constans contiene la mayor parte de parametros que pueden personalizarse para el consumo de la api.

<br>
Librerias externas
<li>Retrofit.</li>
<li>OkHttp.</li>
<li>Picasso</li>
<li>YoutubeAPI</li>

## Vistas/Capa Presentacion
El sistema cuenta con 4 vistas principales:
<br>
Se implementaron dos vistas,  MainActivity y DetailActivity Una para mostrar el conjunto de series o peliculas consultadas y la otra para dar el detalle de cada pelicula o serie seleccionada. 
Las secciones se generan de forma dinamica mediante la generacion de fragmentos, cada fragmento contiene la categoría de series y/o peliculas que mostrara y su detalle

## Requerimientos faltantes

<li>La seccion de Consultas/Busquedas, se puede implementar de varias formar, una de las mas senccillas es crear una actividad y observar los cambios de texto en un EditText con la finalidad de lanzar consultar a la api y mostar los resultados en un RecyclerView con formto GRID.
La otra forma es usar el widget de Search para generar un vista y lanzar la consultas a la api, ya sea directamenta o mediante un proveedor de contenido, esto tambien se puede implementar en un layout sobrepuesto a la actividad.</li>

<li>Modo Offline. Con room se pordría crear la base de datos de los objetos contenidos en el paquete hector.contreras.com.mexflikz.data.models, cada que se reciban con las dos peticiones de la clase MoviesServices en caso de exito actualizar toda la base con las peliculas y series. En caso de estar offline para el detalle no se hará la petición de getVideo para obtener los videos de cada pelicula, solo se mostrara el texto y se intentará mostrar las imagenes que ya venian en la lista de peliculas y/o series </li>

<li>Las transiciones se harian en casa caso colocando un xml anim en cada transicion solicitada</li>

<li>Debe soportar paginado: pendiente</li>

## Mejoras

<li>Uso de flujos de error en las peticiones de la app, no hay implementado un manejo de errores. Así como poner un retry si no tenemos datos ni en cache y no responde el Web Service</li>
<li>Manejo de reproducción de video. Se utilizo la YouTubeAPI, la cual esta desactualizada por no usar androidx, por lo tanto no es posible usar ViewModels, lifecicle y seguir con la arquitecura correcta. Así como su soporte a cambios en configuración en la parte de detalle no esta funcional</li>
<li>Uso de Dagger</li>
<li>Se podría usar un com.google.android.material.appbar.CollapsingToolbarLayout para hacer mas dinamico el Header de las secciones, en detalle podria contener el video o imagen del detalle. En la sección de Series o Peliculas podría contener la parte de busqueda por nombre</li>


## Pruebas Unitarias
Debido al tamaño de la estructura de la app, y por la poca interaccion del usuario, considero que la pruebas Unitarias de Intrumentacion se pueden enfocar a usar Expresso para validar el flujo de cambio de actividad principal y detalle, y generar la serie de pasos para validar esos cambios.

Referente a las pruebas funcionales, podemos evaluar el resultado de una funcion, o un observable, en su momento evaluar el resultado de una funcion o metodo. aqui se me ocurre poder evaluar los metodos anexos al modelo que se implementaron.

## Inyeccion de Dependencias
Utilizar Dagger para la inyeccion de dependencias en la construccion de los Viewmodels o la instanciacion de clases dependientes.

# Mejoras en Usabilidad
<li>Presentar mensajes de alertas cuando el usuario no tenga acceso a internet</li>
<li>Implementar el Refresh mediante el pull del scroll</li>
<li>Atender el evento de cambio de network o conexion para ejecutar un refresh automatico en caso de no haber cargado informacion </li>
<li>Un Onboarding para configuracion de preferencias/idioma/pais</li>
<li>Mejorar la presentacion de la vista del Detalle</li>


----------------------

# Autor
<p>Héctor José Contre - 21-Abril-2022</p>

