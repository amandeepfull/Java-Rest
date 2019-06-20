import React from 'react'

export default class AppInTopView extends React.Component {

    constructor(props){
        super(props);
    
        this.showAppList = this.showAppList.bind(this);
    }

    render() {
        
        return (
            <div class="app-in-top-view">
            <div class= "select-app">
                <span class="app-text"> Select App</span>
                <button onClick={this.showAppList} class="select-app-option">AnywhereWorks
                <img src="/images/down_arrow.jpg"/>
                </button>
                <div class="app-option-wrapper">
                    <ul class="app-list">
                        <li class="app-li">
                            <a href="#">AnywhereWorks</a>
                        </li>
                        <li class="app-li">
                            <a href="#">Setmore</a>
                        </li>
                        <li class="app-li">
                            <a href="#">Answer</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="app-title">
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
    
    }