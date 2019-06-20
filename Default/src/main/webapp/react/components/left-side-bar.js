
import React from 'react'
import LeftSideBarIconBase from './icons/Left-Side-Bar-Icon-Base'

export default class LeftSideBar extends React.Component {

    render() {
        return (

         <nav class="left-side-bar">
         <div class="app-logo"><h2> OAuthCater</h2></div>
        <LeftSideBarIconBase name="Apps" iconImgSrc="/images/icons/app.png"/>
        <LeftSideBarIconBase name="Apps" iconImgSrc="/images/icons/app.png"/>
    
         </nav> 
        )
      }
    }