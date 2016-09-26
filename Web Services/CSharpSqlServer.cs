// set config
/* add this in Web config
  <system.web>
 <webServices>
      <protocols>
        <add name="HttpGet"/>
      </protocols>
    </webServices>
  </system.web>

  */

  [WebMethod]
            [ScriptMethod(ResponseFormat = ResponseFormat.Json, UseHttpGet = true)]
public void Login(string UserName, string Password)  ///Tag =0 devele 1 is add
            {
                JavaScriptSerializer ser = new JavaScriptSerializer();
                string UserID="";
                string Message="";
                try
            {
                SqlDataReader reader;
                using (SqlConnection connection = new SqlConnection(DBConnection.ConnectionString))
                {
                    SqlCommand cmd = new SqlCommand("SELECT UserID FROM Users where UserName=@UserName and Password=@Password ");
                    cmd.CommandType = CommandType.Text;
                    cmd.Connection = connection;
                    cmd.Parameters.AddWithValue("@UserName", UserName);
                    cmd.Parameters.AddWithValue("@Password", Password);
                    connection.Open();
                    reader = cmd.ExecuteReader();
                    while (reader.Read())
                    {
                        UserID = reader.GetInt32(0);
                    }
                  
                    reader.Close();
                    connection.Close();
                }
            }
            catch (Exception ex)
            {
                Message = " cannot access to the data";
            }
       
                var jsonData = new
                {   Message=Message
                    UserID = UserID,
                };
                HttpContext.Current.Response.Write(ser.Serialize(jsonData));
            }


            //login

            [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json, UseHttpGet = true)]
        public void Login(string UserName, string Password)  ///Tag =0 devele 1 is add
        {
            JavaScriptSerializer ser = new JavaScriptSerializer();
           
            string Message = "";
            try
            {

                using (SqlConnection connection = new SqlConnection("Data Source=localhost;Initial Catalog=Users;Integrated Security=True"))
                {
                    SqlCommand cmd = new SqlCommand("Insert into Login(UserName,Password)values(@UserName,@Password) ");
                    cmd.CommandType = CommandType.Text;
                    cmd.Connection = connection;
                    cmd.Parameters.AddWithValue("@UserName", UserName);
                    cmd.Parameters.AddWithValue("@Password", Password);
                    connection.Open();
                    cmd.ExecuteNonQuery();
                    connection.Close();

                    Message = " data is added";
                }
            }
            catch (Exception ex)
            {
                Message = " cannot access to the data"  ;
            }

            var jsonData = new
            {
                Message = Message
                  
            };
            HttpContext.Current.Response.Write(ser.Serialize(jsonData));
        }
