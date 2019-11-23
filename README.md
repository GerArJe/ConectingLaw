# ConectingLaw

Aplicación móvil para facilitar la conexión entre abogados y clientes que requieran de accesoria jurídica.
Esta aplicación tiene el tema sobre derecho, pero puede cambiar mientras siga el principio de conectar personas. 

Esta aplicación contiene:

* Login con correo y contraseña.
* Registro de clientes y abogados.
* Un vista de información del usuario.
* Un vista de los chats que tiene ese usuario.
* Uno menú en la parte inferior utilizado para navegar entre las dos visitas anteriores.
* Selección del tipo de caso. Para acceder a este se debe presionar el floating action button que se encuentra en la vista de los 
chats del usuario.
* Lista de abogados de acuerdo al tipo de caso seleccionado.
* Chat con el abogado y/o cliente.

Componentes de Firebase utilizados:

* Firebase Authentication. Utilizado para manejar la sessió  del usuario.
* Cloud Firestore. utilizado para el registro de los clientes y abogados, además de guardar los chats.
* Cloud Storage. Utilizado para guardar la foto de perfil del usuario(cliente o abogado).
