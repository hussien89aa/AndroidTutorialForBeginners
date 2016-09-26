var express = require('express');
var router = express.Router();
 var url = require('url');
/* GET users listing. */
router.get('/', function(req, res, next) {
  res.header('Access-Control-Allow-Origin', "*"); // security assue
  var queryData = url.parse(req.url, true).query;
  var mysql= require('mysql');
  var config={
    host     : '127.0.0.1',
    user     : 'root',
    password : '12345',
    database : 'resutrant'
  }

  var connection = mysql.createConnection(config);

  connection.connect();

  //connection.query("SELECT * from admin where name='"+queryData.name+ "' and password='"+queryData.password+"'",
  connection.query("INSERT INTO Comment ( PersonName , PersonComment ) VALUES ('"+queryData.PersonName+ "' , '"+queryData.PersonComment+ "' );",

      function(err, rows, fields) {
        if (!err) {

            var error={msg:'data is added'}
            res.send(error);

        }
        else
        {
          var error={msg:'error cannot execute query'}
          res.send(error);
        }
      });

  connection.end();
});

module.exports = router;