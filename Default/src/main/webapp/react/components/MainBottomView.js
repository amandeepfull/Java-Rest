import React from 'react'
import LeftSideBar from "./Left-Side-Bar"
import RightSideBar from "./Right-Side-Bar"
export default class MainBottomView extends React.Component {

    render() {
        return (
         <div class="main-bottom-view">
         <LeftSideBar/>
         <RightSideBar/>
         </div> 
        )
      }
    }