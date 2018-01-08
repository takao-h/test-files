import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {
  getInitialState() {
   return {testValue: "Hello World!!!"};
 }
  hundleChange(event){
    this.setState({
      testValue: event.target.value
    })
  }
  submitHundler(event){
    event.preventDefault();
    alert(this.state.testValue);
  }
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome to React</h1>
        </header>
        <p className="App-intro">
          To get started, edit <code>src/App.js</code> and save to reload.
        </p>
        <form onSubmit={this.submitHandler}>
        <input type="text" value={this.state.testValue} onChange = {this.handleChange} />
        <button type="submit">
          送信！!!!
        </button>
      </div>
    );
  }
}

export default App;
