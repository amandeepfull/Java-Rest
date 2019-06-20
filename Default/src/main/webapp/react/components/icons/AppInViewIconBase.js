import React from 'react'

export default class AppInViewIconBase extends React.Component {
constructor(props){
    super(props)
}
    render() {

        return (
            <a class="app-in-view-bar-icon-base" href="#"><img src={this.props.iconImgSrc} width="20px" /><span class="app-in-view-icon-title" title={this.props.name} >{this.props.name}</span></a>
        )
      }
    }