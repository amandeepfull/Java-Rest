import React from 'react'

class AddApp extends React.Component {



  render() {
    return (
      
    

        <div id="add_app_view" class="form-group">
        <h2> Register App</h2>
        <div id="add_app_view_1" >
        Name<input type="text" id="app_name" class="form-control"/><br/>
        Host Url <input type="text" id="app_host_url" class="form-control"/><br/>
        App Id<input type="text" id="app_id" class="form-control"/><br/>
        Client Id <input type="text" id="app_client_id" class="form-control"/><br/>
        Client Secret <input type="text" id="app_client_secret" class="form-control"/><br/>

        <button class="btn btn-primary">Register</button>
        </div>
        <div id="add_app_view_2">
        Token Expiry (in secs) <input type="text" id="app_token_expiry" class="form-control"/><br/>
        Oauth Callback Uri <input type="text" id="app_redirect_uri" class="form-control"/><br/>
        Scopes <textarea id="app_scopes" class="form-control"></textarea>

        
        </div>
       
        </div>
        
     
    )
  }
}

export default AddApp;
