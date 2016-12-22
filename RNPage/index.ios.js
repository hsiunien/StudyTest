/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */
'use strict'
import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  Image
} from 'react-native';

export default class RNPage extends Component {
  render() {
    return (
      <View style={styles.container}>
           <View style={[styles.box,{alignItems:"center",justifyContent:"center"}]}>
              <View style={[styles.boxBase,{alignSelf:'center'}]}></View>
           </View>
           <View style={styles.box}>
              <View style={[styles.boxBase,{alignSelf:'flex-start'}]}></View>
              <View style={[styles.boxBase,{alignSelf:'flex-end'}]}></View>
           </View>
           <View style={styles.box}>
              <View style={[styles.boxBase,{alignSelf:'flex-start'}]}></View>
              <View style={[styles.boxBase,{alignSelf:'center'}]}></View>
              <View style={[styles.boxBase,{alignSelf:'flex-end'}]}></View>
           </View>
           <View style={[styles.box,{flexDirection:'column',justifyContent:'space-between'}]}>
              <View style={[styles.column,{justifyContent:"space-between"}]}>
                    <View style={[styles.boxBase]}></View>
                    <View style={[styles.boxBase]}></View>
              </View>
              <View style={[styles.column,{ justifyContent:"space-between"}]}>
                  <View style={[styles.boxBase]}></View>
                  <View style={[styles.boxBase]}></View>
              </View>
            </View>
           <View style={[styles.box,{flexDirection:'column',justifyContent:'space-between'}]}>
             <View style={[styles.column,{justifyContent:"space-between"}]}>
                   <View style={[styles.boxBase]}></View>
                   <View style={[styles.boxBase]}></View>
             </View>
             <View style={[styles.boxBase]}></View>
             <View style={[styles.column,{justifyContent:"space-between"}]}>
                 <View style={[styles.boxBase]}></View>
                 <View style={[styles.boxBase]}></View>
             </View>
           </View>
           <View style={[styles.box,{flexDirection:'column',justifyContent:'space-between'}]}>
             <View style={[styles.column,{justifyContent:"space-between"}]}>
                   <View style={[styles.boxBase]}></View>
                   <View style={[styles.boxBase]}></View>
             </View>
             <View style={[styles.column,{justifyContent:"space-between"}]}>
                 <View style={[styles.boxBase]}></View>
                 <View style={[styles.boxBase]}></View>
             </View>
             <View style={[styles.column,{justifyContent:"space-between"}]}>
                 <View style={[styles.boxBase]}></View>
                 <View style={[styles.boxBase]}></View>
             </View>
           </View>
      </View>


    );
  }
}

const styles = StyleSheet.create({

  container: {
    backgroundColor: '#ffffff',
    flex:1,
    flexDirection:'row',
    flexWrap:'wrap'
  },
  box:{
        backgroundColor: '#F5FCFF',
        height:120,
        width:120,
        margin:5,
        padding:10,
        borderRadius:10,
        borderWidth:5,
        borderColor:"#999999",
        justifyContent:'space-between',
        alignItems:'center'
    },
  boxBase: {
    backgroundColor: '#000000',
    width:20,
    height:20,
    borderRadius:10,
    margin:5,
    maxWidth:50
  },
  column:{
    width:100,
    flexDirection:'row',
  }
});

AppRegistry.registerComponent('RNPage', () => RNPage);
