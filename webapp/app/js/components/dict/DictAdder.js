var React = require('react');
var bs = require('react-bootstrap');
var Input = bs.Input;
var DictAdder = React.createClass({
  getInitialState: function() {
    return {value: ''};
  },
  handleSubmit: function(e) {
    e.preventDefault();
    var name = this.state.value;
    var dicts = this.props.dicts;
    var target = this.props.target;
    if (!(name in dicts.value)) {
      dicts.value[name] = {name: name, positive: [], negative: []};
      dicts.requestChange(dicts.value);
      target.requestChange(name);
    }
    this.setState({value: ''});
  },
  onChange: function(e) {
    this.setState({value: e.target.value});
  },
  render: function() {
    return (
      <form onSubmit={this.handleSubmit}>
        <Input
          type="text"
          onChange={this.onChange}
          value={this.state.value}
          placeholder="Create New Dictionary"/>
      </form>
    );
  }
});
module.exports = DictAdder;