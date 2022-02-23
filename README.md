Smart Home
----------

# Installation
**Setup _postgreSQL_ database:**

1. Install postgreSQL from [here](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads). 
   - Please note: 
     - Remember the password you choose during the installation process for later.
     - Leave the default port. (5432)
     - At the end of the installation do not install _stack builder_ when prompted.

2. Once the installation process is complete, open pgAdmin from the start menù on Windows (Linux & Mac procedure [here](https://www.enterprisedb.com/postgres-tutorials/connecting-postgresql-using-psql-and-pgadmin).
   )
3. Insert installation password when prompted. 
4. Open "Servers" toggle menù and type the same password.
5. Create new database 
   1. Right click on "_Databases_" then "_Create_" > "_Database..._"
   2. Call it "_smarthome_" with owner "_postgres_"
   4. Open tab "_Security_" and crate new Privileges: 
      1. Grantee: _postgres_ 
      2. Privileges: _all_
   5. Save.
   
**Setup Repository:**
1. Clone the repository
2. Make sure that in `src.main.resources.application.properties` url, username and password are set correctly based on your database installation.

# Usage
The project contains both the _Home Simulation_ and the _SmartHome Application_.
- To start the **Home Simulation** execute: `src.main.java.io.patriot_framework.HomeSimulation`
- To start the **SmartHome Application** execute: `src.main.java.com.smarthome.smarthome.SmartHomeApplication`