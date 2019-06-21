import React from 'react';

export default class NewAppPopup extends React.Component {

    constructor(props){
        super(props);
        this.handlePopupClose = this.handlePopupClose.bind(this);
    }
    render() {
        return (

         <div className="new-app-popup">
          <div className="close" onClick={this.handlePopupClose}>x</div>
        <h3 className="app-text">New App</h3>
            <p className="app-text">Projects are a way to specify custom branding related information and also to group certain functionalities for individual product within a company</p>
           <hr></hr>
           <span className="app-text" >Display Name </span><input class  type="text"/><br/>
           <span className="app-text" >Unique Name </span><input type = "text"/><br/>
           <hr></hr>
           <button className="create-app-btn">Create App</button> 
         </div> 
        )
      }

      handlePopupClose(){
        document.getElementsByClassName("overlay")[0].style.display = "none";
        document.getElementsByClassName("new-app-popup")[0].style.display = "none" 
      }
    }