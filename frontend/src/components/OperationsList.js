import React from "react";
import { connect } from 'react-redux';
import { Box, Col } from 'adminlte-2-react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import PopUp from "./PopUp";
import { servicesList } from "../ActionCreator";
import { url_lista_serviziFake } from "../REST";
import axios from "axios";
import { Accordion, Alert, Card } from "react-bootstrap";
import { getErrorToast, getLoadingToast, stopLoadingToast } from "../toastManager";
import Collapsible from "react-collapsible";

/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
const mapDispatchToProps = dispatch => ({
    ServicesList: (services) => {
      dispatch(servicesList(services))
    }
  }
);

/**
 * connect the redux state to the component
 * @param {*} state 
 */
const mapStateToProps = state => ({
    client_list: state.client_list,
    token: state.token,
    services_list: state.services_list
  }
);

const OperationsList = (props) => {

  const getServicesList = (selected, token) => {
    const loadingToast = getLoadingToast("Caricamento...");
    axios.post(url_lista_serviziFake, {
      nome_client: selected
    })
    .then(function (response) {
      stopLoadingToast(loadingToast);
      let list = servicesListMaker(response.data.servizi);
      props.ServicesList(list)
    })
    .catch(function (error) {
      stopLoadingToast(loadingToast);
      getErrorToast(String(error));
    });
  }

  const openToggle = (id) => {
    var elem = document.getElementsByClassName("arrowAccordion"+id)[0];
    elem.style.transform = "rotate(90deg)";
    elem.style.transition = "transform 1s ease";
    elem.style.display = "inline-block";
  }

  const closeToggle = (id) => {
    var elem = document.getElementsByClassName("arrowAccordion"+id)[0];
    elem.style.transform = "rotate(0deg)";
    elem.style.transition = "transform 1s ease";
    elem.style.display = "inline-block";
  }

  const servicesListMaker = (services) => {
    let returnList = [<p class="infoDisabled">* Operazione non selezionabile con la licenza in uso.</p>];
    let status = ["", "RUNNING", "PROBLEMI", "WARINNG"]
    services.map((service, i) => {
      switch(service.stato){
        case "0":
          returnList = getCard(returnList, service.nome, false, i+1);
          break;
        default:
          returnList = getCard(returnList, service.nome, true, i+1);
      }
    })
    return returnList;
  }
  
  const getCard = (returnList, opname, enabled, i) => {
    returnList = [returnList, 
      <Collapsible onOpen={()=>openToggle(i)} onClose={()=>closeToggle(i)} trigger={<div className="clickable"><h4><span className={"arrowAccordion"+i}><FontAwesomeIcon icon={["fas", "chevron-right"]} /></span> {opname}</h4></div>}>
        {enabled 
          ? <p><input type="checkbox" checked/> <label>Abilita operazione</label></p>
          : <p><input type="checkbox"/> <label>Abilita operazione</label></p>
        }
        <h5>Descrizione:</h5>  
      </Collapsible>

  	]

  return returnList
  }

  return (
    <Box title="Lista delle operazioni" type="primary" collapsable footer={<PopUp title="Gestione delle operazioni" linkClass={"clickable"} childs={props.services_list} action={()=>getServicesList(props.selected, props.token)}/>}>
      <Col md={12} xs={12}>
        <h4><FontAwesomeIcon icon={["fas", "check-circle"]} /> Operazioni attive: {props.ops[0]}</h4>
        <h4><FontAwesomeIcon icon={["fas", "times-circle"]} /> Operazioni con problemi: {props.ops[2]}</h4>
        <h4><FontAwesomeIcon icon={["fas", "exclamation-circle"]} /> Operazioni con warnings: {props.ops[3]}</h4>
      </Col>
    </Box>
  );
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(OperationsList);