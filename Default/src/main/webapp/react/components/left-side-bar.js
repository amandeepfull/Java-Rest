
import React from 'react'
import LeftSideBarIconBase from './icons/Left-Side-Bar-Icon-Base'

export default class LeftSideBar extends React.Component {

    render() {
        return (

         <nav className="left-side-bar">
         <div className="app-logo"><h2> OAuthCater</h2></div>
        <LeftSideBarIconBase name="Apps" iconImgSrc="/images/icons/app.png"/>
        <LeftSideBarIconBase name="Profile" iconImgSrc="/images/icons/profileIcon.png"/>
    
         </nav> 
        )
      }
    }