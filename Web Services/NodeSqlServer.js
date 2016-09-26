var Connection=require('tedious').Connection;
var Config={
    userName:"DB_9E3E00_testing_admin",
    password:'123456789',
    server:'SQL5019.Smarterasp.net'

};

var myconnection=new Connection(Config);

myconnection.on('connect', function(err){


    console.log("we connected suffecuflly");
    getdatae();
});

var Request = require('tedious').Request;
var TYPES = require('tedious').TYPES;
function getdatae() {
    request = new Request("SELECT * FROM [DB_9E3E00_testing].[dbo].[user]", function(err) {
        if (err) {
            console.log(err);}
    });
    var result = "";
    request.on('row', function(columns) {
        columns.forEach(function(column) {
            if (column.value === null) {
                console.log('NULL');
            } else {
                result+= column.value + " ";
            }
        });
        console.log(result);
        result ="";
    });


    myconnection.execSql(request);
}

function insertdata() {

    request = new Request("INSERT into [DB_9E3E00_testing].[dbo].[user] (firstname,lastname)VALUES (@firstname,@lastname);", function(err) {
        if (err) {

            console.log(err);}
    });
    request.addParameter('firstname', TYPES.NVarChar,'hussein');
    request.addParameter('lastname', TYPES.NVarChar , 'alrubaye');

    request.on('row', function(columns) {
        columns.forEach(function(column) {
            if (column.value === null) {
                console.log('NULL');
            } else {
                console.log("item of inserted item is " + column.value);
            }
        });
    });
    myconnection.execSql(request);
}