import React from 'react';
import AutobuskaAxios from './../../apis/AutobuskaAxios';

class EditLinija extends React.Component {

    constructor(props) {
        super(props);




        this.state = { linijaId: -1, brojMesta: 0, cenaKarte: 0, destinacija: "", vremePolaska: "", prevoznici: [], idPrevoznika: -1 }

    }

    componentDidMount() {
        this.getLinijaById(this.props.match.params.id);
        this.getPrevoznici();
        console.log("test2");
    }



    getLinijaById(linijaId) {
        AutobuskaAxios.get('/linije/' + linijaId)
            .then(res => {
                // handle success
                console.log(res);
                this.setState({
                    linijaId: res.data.id, brojMesta: res.data.brojMesta,
                    cenaKarte: res.data.cenaKarte, destinacija: res.data.destinacija,
                    vremePolaska: res.data.vremePolaska, prevoznik: res.data.prevoznik.naziv,
                    idPrevoznika: res.data.prevoznik.id
                });
            })
            .catch(error => {
                // handle error
                console.log(error);
                alert('Error occured please try again!');
            });

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


    onBrojMestaChange = event => {
        console.log(event.target.value);

        const { name, value } = event.target;
        console.log(name + ", " + value);

        this.setState((state, props) => ({
            brojMesta: value
        }));
    }

    onCenaKarteChanged = event => {
        console.log(event.target.value);

        const { name, value } = event.target;
        console.log(name + ", " + value);

        this.setState((state, props) => ({
            cenaKarte: value
        }));
    }

    onDestinacijaChanged = event => {
        console.log(event.target.value);

        const { name, value } = event.target;
        console.log(name + ", " + value);

        this.setState((state, props) => ({
            destinacija: value
        }));
    }

    onVremePolaskaChanged = event => {
        console.log(event.target.value);

        const { name, value } = event.target;
        console.log(name + ", " + value);

        this.setState((state, props) => ({
            vremePolaska: value
        }));
    }

    travelerSelectionChanged = event => {
        console.log(event.target.value);

        const { name, value } = event.target;
        console.log(name + ", " + value);

        this.setState((state, props) => ({
            idPrevoznika: value
        }));
    }




    async edit(e) {
        e.preventDefault();

        try {

            const prevoznik = this.state.prevoznici.find(prevoznik => prevoznik.id == this.state.idPrevoznika)
            const linijaDTO = {
                id: this.state.linijaId,
                brojMesta: this.state.brojMesta,
                cenaKarte: this.state.cenaKarte,
                vremePolaska: this.state.vremePolaska,
                destinacija: this.state.destinacija,
                prevoznik: prevoznik
            }
            console.log(linijaDTO)
            let response = await AutobuskaAxios.put('/linije/' + this.state.linijaId, linijaDTO)

            // handle success
            console.log(response);
            alert('Linija was edited successfully!');
            this.props.history.push('/linije');


        } catch (error) {
            alert("Couldn't save the linija");
        }
    }

    /* edit() {
         
         const prevoznik = this.state.prevoznici.find(prevoznik => prevoznik.id==this.state.idPrevoznika)
         const linijaDTO = { 
             id:this.state.linijaId,
             brojMesta:this.state.brojMesta,
             cenaKarte:this.state.cenaKarte,
             vremePolaska:this.state.vremePolaska,
             destinacija:this.state.destinacija,
             prevoznik: prevoznik
         }
         console.log(linijaDTO)
        AutobuskaAxios.put('/linije/' + this.state.linijaId, linijaDTO)
         .then(res => {
             // handle success
             console.log(res);
             alert('Linija was edited successfully!');
             this.props.history.push('/linije');
         })
         .catch(error => {
             // handle error
             console.log(error);
             alert('Error occured please try again!');
          });
     } */


    render() {
        return (
            <div>
                <h1>Autobuska stanica Subotica</h1>
                <h2>Izmena linije</h2>
                <form>
                    <label htmlFor="pBrojMesta">Broj mesta</label>
                    <input type="number" id="brMes" name="brojMesta" value={this.state.brojMesta} onChange={(e) => this.onBrojMestaChange(e)} /><br />
                    <label htmlFor="pCenaKarte">Cena Karte</label>
                    <input type="number" step=".01" id="cK" name="cenaKarte" value={this.state.cenaKarte} onChange={(e) => this.onCenaKarteChanged(e)} /><br />
                    <label htmlFor="pDestinacija">Destinacija</label>
                    <input id="dS" name="destinacija" value={this.state.destinacija} onChange={(e) => this.onDestinacijaChanged(e)} /><br />
                    <label htmlFor="pVremePolaska">Vreme Polaska</label>
                    <input id="vR" name="vremePolaska" value={this.state.vremePolaska} onChange={(e) => this.onVremePolaskaChanged(e)} /><br />
                    <label htmlFor="pTraveler">Prevoznik</label>
                    <select id="pTravler" value={this.state.idPrevoznika} onChange={(e) => this.travelerSelectionChanged(e)}>
                        <option></option>
                        {
                            this.state.prevoznici.map((prevoznik) => {
                                return (
                                    <option key={prevoznik.id} value={prevoznik.id}>{prevoznik.naziv}</option>
                                )
                            })
                        }
                    </select><br />

                    <button className="btn btn-primary" onClick={(e) => { this.edit(e) }}>Izmeni</button>
                </form>
            </div>
        )
    }



}


export default EditLinija;