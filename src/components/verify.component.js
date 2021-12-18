import React, { Component } from "react";

import UserService from "../services/user.service";

export default class Verify extends Component {
  constructor(props) {
    super(props);

    this.state = {
      content: ""
    };
  }

  componentDidMount() {
    const query = new URLSearchParams(this.props.location.search);
    const vcode = query.get('code');
    console.log(vcode);
    UserService.getVerify(vcode).then(
      response => {
        this.setState({
          content: response.data
        });
      },
      error => {
        this.setState({
          content:
            (error.response && error.response.data) ||
            error.message ||
            error.toString()
        });
      }
    );
  }

  render() {
    return (
      <div className="container">
        <header >
          <h3>{this.state.content}</h3>
        </header>
      </div>
    );
  }
}
