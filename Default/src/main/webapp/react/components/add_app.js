import React from 'react'
import Ajax from '../services/Ajax'
import CommonConstants from '../CommonContansts'
import AppInfoPopupView from './app_info_popup_view'

class AddApp extends React.Component {

  constructor(props){
    super(props);
    this.state ={
      clientId : "",
      clientSecret : "",
      appId : ""
    }
    this.handleRegisterApp = this.handleRegisterApp.bind(this);
    this.ajax = new Ajax();
  }

 

  handleRegisterApp(){

    let loader = document.getElementById("loading");
    loader.style.display = "block";

    document.getElementById("server-error-msg").innerHTML = "";

    let appName = document.getElementById("app_name").value.trim();
    let redirectUri = document.getElementById("app_redirect_uri").value.trim();
    let scopes = document.getElementById("app_scopes").value.trim();
    scopes = scopes.split(",");
    

    var newScopes = [];
    for( var i = 0 ; i < scopes.length ; i++){
      newScopes.push(scopes[i].trim());
      }
  
    let json = {
      name : appName,
      redirectUris : [redirectUri],
      scopes : newScopes,
      createdBy : currentUserId,
      type : "web"
    }

    
    var url = CommonConstants.getApiUrl("api/v1/app");
    
    this.ajax.makeRequest("POST",url, json).then((resp) =>{

      loader.style.display = "none";

      var app = resp.data.app;

      if(!resp.ok){
        document.getElementById("server-error-msg").innerHTML = "Error msg : "+resp.errors[0].message;
      }else{
          var popup = document.getElementById("myPopup");
          popup.classList.toggle("show");
        
          this.setState({

            clientId : "clientId : "+app.clientId,
            clientSecret : "clientSecret : "+app.clientSecret,
            appId : "appId : "+app.id
          })
      }




    });
    

   
    
  }

  render() {
    return (
  
        <div id="add_app_view" class="form-group">
        <h2> Register App</h2>
        <div id="server-error-msg"></div>
        <div id="add_app_view_1" >

      <div id="loading"></div>
        <div class="popup" >
    <div class="popuptext" id="myPopup"><div id="app-credentials-popup">
      <AppInfoPopupView appId={this.state.appId} clientId={this.state.clientId} clientSecret={this.state.clientSecret}/>
         </div></div>
    </div>
        Name<input type="text" id="app_name" class="form-control"/><br/>
        Host Url <input type="text" id="app_host_url" class="form-control"/><br/>
       
        <button class="btn btn-primary" onClick={this.handleRegisterApp}>Register</button>
        </div>
        <div id="add_app_view_2">
        Oauth Callback Uri <input type="text" id="app_redirect_uri" class="form-control"/><br/>
        Scopes <textarea id="app_scopes" class="form-control"></textarea>
        Note: add comma(,) separated scopes.
      
        </div>
       
        </div>
        
     
    )
  }
}

export default AddApp;
