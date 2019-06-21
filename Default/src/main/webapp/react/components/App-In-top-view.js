import React from 'react'

export default class AppInTopView extends React.Component {

    constructor(props){
        super(props);
    
        this.showAppList = this.showAppList.bind(this);
        this.showNewAppPopup = this.showNewAppPopup.bind(this);

    }

    render() {
        
        return (
            <div className="app-in-top-view">
            <div className= "select-app">
                <span className="app-text"> Select App</span>
                <button onClick={this.showAppList} className="select-app-option">AnywhereWorks
                <img src="/images/down_arrow.jpg"/>
                </button>
                <div className="app-option-wrapper">
                    <ul className="app-list">
                        <li className="app-li">
                            <a href="#">AnywhereWorks</a>
                        </li>
                        <li className="app-li">
                            <a href="#">Setmore</a>
                        </li>
                        <li className="app-li">
                            <a href="#">Answer</a>
                        </li>
                        <li className="app-li">
                            <a href="#">Answer</a>
                        </li>
                        <li className="app-li">
                            <a href="#">Answer</a>
                        </li>
                        <li className="app-li">
                            <a href="#">Answer</a>
                        </li>
                        <li className="app-li">
                            <a href="#">Answer</a>
                        </li>
                        <li className="app-li">
                            <a href="#">Answer</a>
                        </li>
                        <li className="app-li">
                            <a href="#">Answer</a>
                        </li>
                        <li className="app-li">
                            <a href="#">Answer</a>
                        </li>
                        <li className="app-li">
                            <a href="#">Answer</a>
                        </li>
                        <li className="app-li">
                            <a href="#">Answer</a>
                        </li>
                        <li className="app-li">
                            <a href="#">Answer</a>
                        </li>

                    </ul>
                </div>
                </div>
                <button className="new-app-btn" onClick={this.showNewAppPopup}>
                <img src="/images/icons/plusIcon.png"/>New App
                </button>
        
            <div className="app-title">
            <h2>Anywhereworks</h2>
            </div>
                       
            </div>

        )
      }

    showAppList(){
        const applistWrapperDisplayStyle = document.getElementsByClassName("app-option-wrapper")[0].style.display;
        if(applistWrapperDisplayStyle === "")
        document.getElementsByClassName("app-option-wrapper")[0].style.display = "block"
         else
         document.getElementsByClassName("app-option-wrapper")[0].style.display = "";   
    }

    showNewAppPopup(){
         document.getElementsByClassName("overlay")[0].style.display = "block";
         document.getElementsByClassName("new-app-popup")[0].style.display = "block"
    }
    
    }