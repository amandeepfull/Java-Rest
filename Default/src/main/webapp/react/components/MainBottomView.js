import React from 'react'
import LeftSideBar from "./Left-Side-Bar"
import RightSideBar from "./Right-Side-Bar"
import NewAppPopup from './popups/NewAppPopup'

export default class MainBottomView extends React.Component {

    render() {
        return (
         <div className="main-bottom-view">
         <LeftSideBar/>
         <NewAppPopup/> 
         <RightSideBar/>
         </div> 
        )
      }
    }