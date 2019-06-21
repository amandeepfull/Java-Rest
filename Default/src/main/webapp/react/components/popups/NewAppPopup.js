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
         </div> 
        )
      }

      handlePopupClose(){
        document.getElementsByClassName("overlay")[0].style.display = "none";
        document.getElementsByClassName("new-app-popup")[0].style.display = "none" 
      }
    }