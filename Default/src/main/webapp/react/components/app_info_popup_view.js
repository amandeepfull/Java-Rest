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

      appId<input type="text" id="app_Id" /><br/>
        
      clienId<input type="text" id="clien_Id" /><br/>
      
      client secret<input type="text" id="client_secret"/><br/>
      
      <button type="button" class="btn btn-primary" onClick={this.handlick}>Close</button>
     
      
     </div>
    )


  }
}
