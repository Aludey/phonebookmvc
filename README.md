# PhonebookMVC
Final project for Spring Course.

This application is an improvised Phone Book, that can contain Contacts with name and list of phones. 

You can add contacts, view contacts, remove contact, update contact, and add/remove phones from a contact.

## How to run project:
1. Open project as Maven project in IntelliJ IDEA.
2. Start Local MySQL server (URL to server should be declared in `spring.datasource.url` variable in `application.properties` file , e.g. localhost:3306)
4. DB username and password should be declaired in `spring.datasource.username` an `spring.datasource.password` variables in `application.properties` file.
4. Application server port is declaired in `server.port` variable in `application.properties` file.
5. Run `main()` from `PhonebookmvcApplication` class.
6. You can use `POSTMAN` to send requests.

## Supported REST requests:
 - `POST /api/v1/customers/addContact` - Adds one contact to DB. `String name` and `List<String> phones` should be provided as JSON object.
 - `POST /api/v1/addContacts` - Adds several contacts to DB. `List` of contacts, same as in `/AddContact` request, should be provided as JSON object.
 - `GET /api/v1/getContacts` - Returns all contacts from DB.
 - `GET /api/v1/getContact/{name}` - Returns contact specified by `{name}` from DB.
 - `DELETE /api/v1/deleteContact/{name}` - Deletes contact specified by `{name}` from DB.
 - `PUT /api/v1/updateContact` - Updates contact. Updated contact same as in `/AddContact` request, should be provided as JSON object and contact name should already exist in DB.
 - `PUT /api/v1/addPhones/{name}` - Adds `List<String> phones` to contact specified by `{name}`. `List<String> phones` should be provided as JSON object.
 - `PUT /api/v1/deletePhones/{name}` - Removes `List<String> phones` from contact specified by `{name}`. `List<String> phones` should be provided as JSON object.
    
