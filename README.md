# Inventory_Management
Version 2.2.0
Built on April 19th, 2020.
Major UI Changes & Some Features Added
+ Dashboard View
+ Clear History of Manufactured and Dispatched View
+ Bigger and Better Font
+ Product wise separation of colour and weights

Version 2.0.0
Built on April 5th, 2020.

## Features:
-> Database powered by MongoDb.

-> Removed sqlite3. No more manual handling of Db.

Version 1.0.2
Built on November 10th, 2019.

Inventory Management System or Stock Management System built using Java Swing and Sqlite. It's a stand-alone JAR. Maven is used for buliding the JAR. For storing data, Sqlite3 is used.

## Release Note:
-> New Interface introduced with 3 views - Production, Dispatch and Stock.

-> Supports real-time changes in product, colour and weight dropdown.

-> Loosely coupled component architecture.

-> Production datetime and Dispatch datetime column added into the view.

-> Hardcoding of DB path is remvoed. config.properties file intoduced.

### Features:
-> Adding and removing of manufactured or dispatched products.

-> User can add or remove products, colours and weights in Menu.

-> Offline Sqlite3 Database.

-> Stand-alone JAR. One click execution on any Java-enabled system.

### Prerequisites:
-> JRE 1.8

-> Sqlite3.dll (To customize database schema)

### Configuration:

-> Download **ims_files** directory and **IMS-1.0.2.jar**.

-> Put **ims_files** directory in C:\ drive(This step is for Windows users only.).

-> Create your DB and provide its path in **config.properties** in **ims_files**. For example, sample DB is provided as **rbp.db**