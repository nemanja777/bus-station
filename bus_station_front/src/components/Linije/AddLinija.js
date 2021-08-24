import React from 'react';
import AutobuskaAxios from './../../apis/AutobuskaAxios';
import { Form, Row, Col } from "react-bootstrap";

class AddLinija extends React.Component {

    constructor(props) {
        super(props);

        let linija = {
            brojM: 0,
            price: 0.00,
            destination: "",
            time: "",
            traveler: null
        }

        this.state = { linija: linija, prevoznici: [] }

    }

    componentDidMount() {
        this.getPrevoznici();
        console.log("test2");
    }

    async getPrevoznici() {
        try {
            let result = await AutobuskaAxios.get("/prevoznici");
            let prevoznici = result.data;
            this.setState({ prevoznici: prevoznici });
            console.log("test1");
        } catch (error) {
            console.log(error);
            alert("Couldn't fetch prevoznici!");
        }
    }

    async create(e) {
        e.preventDefault();

        try {
            let linija = this.state.linija;
            let linijaDTO = {
                brojMesta: linija.brojM,
                cenaKarte: linija.price,
                vremePolaska: linija.time,
                destinacija: linija.destination,
                prevoznik: linija.traveler
            }
            console.log(linijaDTO);
            let response = await AutobuskaAxios.post("/linije", linijaDTO);
            console.log(response);
            this.props.history.push("/linije");
        } catch (error) {
            alert("Couldn't save the linija");
        }
    }

    valueInputChanged(e) {
        let input = e.target;

        let name = input.name;
        let value = input.value;

        let linija = this.state.linija;
        linija[name] = value;

        this.setState({ linija: linija });
    }

    travelerSelectionChanged(e) {

        let prevoznikId = e.target.value;
        let prevoznik = this.state.prevoznici.find((prevoznik) => prevoznik.id == prevoznikId);

        let linija = this.state.linija;
        linija.traveler = prevoznik;

        this.setState({ linija: linija });

    }









    render() {
        return (
            <>
                <Row>
                    <Col></Col>
                    <Col xs="12" sm="10" md="8">
                        <h1>Autobuska stanica Subotica</h1>
                        <h2>Dodaj novu linuju</h2>
                        <Form>
                            <Form.Label htmlFor="pBrojMesta">Broj mesta</Form.Label>
                            <Form.Control type="number" id="brMes" name="brojM" onChange={(e) => this.valueInputChanged(e)} /><br />
                            <Form.Label htmlFor="pCenaKarte">Cena Karte</Form.Label>
                            <Form.Control type="number" step=".01" id="cK" name="price" onChange={(e) => this.valueInputChanged(e)} /><br />
                            <Form.Label htmlFor="pDestinacija">Destinacija</Form.Label>
                            <Form.Control id="dS" name="destination" onChange={(e) => this.valueInputChanged(e)} /><br />
                            <Form.Label htmlFor="pVremePolaska">Vreme Polaska</Form.Label>
                            <Form.Control id="vR" name="time" onChange={(e) => this.valueInputChanged(e)} /><br />
                            <Form.Label htmlFor="pTraveler">Prevoznik</Form.Label>
                            <div class="input-group">
                                <select class="custom-select" id="inputGroupSelect04" onChange={event => this.travelerSelectionChanged(event)}>
                                    <option></option>
                                    {
                                        this.state.prevoznici.map((prevoznik) => {
                                            return (
                                                <option key={prevoznik.id} value={prevoznik.id}>{prevoznik.naziv}</option>
                                            )
                                        })
                                    }
                                </select><br /><br /><br />
                            </div>

                            <button id="dugme" className="btn btn-primary" onClick={(event) => { this.create(event); }}>DODAJ</button>

                        </Form>
                    </Col>
                    <Col></Col>
                </Row>
            </>

        )
    }


}

export default AddLinija;