import React from 'react'
import AddApp from './add_app';

export default class AppInfoPopupView extends React.Component {

constructor(props){
    super(props);

   
    this.handlick = this.handlick.bind(this);

}

handlick(){
   
        var appInfoViewPopup =  document.getElementById("myPopup");
        appInfoViewPopup.style.visibility = "hidden";
      
}
  render() {
 
    return (
    
     <div id="app-info-popup-view">
     <h2>Registered App Credentials</h2>

     <input type="text" id="app_Id" value={this.props.appId} class="form-control-app-info-popup"/><br/>
        
       <input type="text" id="client_Id" value= {this.props.clientId}class="form-control-app-info-popup"/><br/>
      
        <input type="text" id="client_secret" value={this.props.clientSecret} class="form-control-app-info-popup"/><br/>
      
      <button type="button" class="btn btn-primary" onClick={this.handlick}>Close</button>
     
      
     </div>
    )


  }
}
