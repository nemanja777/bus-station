import React from 'react';
import ReactDOM from 'react-dom';
import { Route, Link, HashRouter as Router, Switch } from 'react-router-dom';
import {Container, Button,  Navbar, Nav} from "react-bootstrap";
import Login from "./components/login/Login";
import Home from './components/Home';
import AddLinija from './components/Linije/AddLinija';
import Linije from './components/Linije/Linije';
import NotFound from './components/NotFound';
import {logout} from './services/auth';
import EditLinija from './components/Linije/EditLinija';

function changeImage() {  
    console.log('aaaaaa') 
    var BackgroundImg=["/bus1.jpg",
        "/bus1.jpg",
        "/bus2.jpg",
        "/bus3.jpg"
    ];
    var i = Math.floor((Math.random() * 4));
    document.getElementById('scania').style.backgroundImage = `url(${process.env.PUBLIC_URL  + BackgroundImg[i]  })`;
}
window.setInterval(changeImage, 5000);






class App extends React.Component{

    render(){
        return(
            <div id="scania" style = {{
                backgroundImage: `url(${process.env.PUBLIC_URL + '/scania.jpg'})`,
                backgroundPosition: 'center',
                backgroundSize: 'cover',
                backgroundRepeat: 'no-repeat',
                width: 'calc(100vw - 20px)',
                height: '150vh'
            }}>
            <div>
                <Router>
                <Navbar sticky="top"  expand bg="dark" variant="dark">
                        <Navbar.Brand as={Link} to="/">
                        <img
        src="/jgspns-logo.png"
        width="50"
        height="40"
        className="d-inline-block align-top"
        alt=""
      />
                        </Navbar.Brand>
                        <Nav>
                            <Nav.Link as={Link} to="/linije">Linije</Nav.Link>
                    
                            {window.localStorage['jwt'] ? 
                             <Nav.Link  className="float-right pull-right" onClick = {()=>logout()}>Logout</Nav.Link> :
                            <Nav.Link className="pull-right" as={Link} to="/login">Log in</Nav.Link>
                            }
                        </Nav>
                           
                        </Navbar>
                           

                    <Container style={{paddingTop:"25px"}}>
                    <Switch>
                        <Route exact path="/" component={Home} />
                        <Route exact path="/login" component={Login}/>
                        <Route exact path="/linije" component={Linije} />
                        <Route exact path="/linije/add" component={AddLinija} />
                        <Route exact path="/linije/edit/:id" component={EditLinija} />
                        <Route component={NotFound} />
                    </Switch>
                    </Container>
                </Router>

                </div>
            </div>
        )
    }




};

   


    ReactDOM.render(
        <App/>,
        document.querySelector('#root')
    );