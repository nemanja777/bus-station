import React from 'react';
import AutobuskaAxios from './../../apis/AutobuskaAxios';
import { Table, Button, Form } from 'react-bootstrap'
import './../../index.css';






class Linije extends React.Component {

  constructor(props) {
    super(props);

    this.state = {
      linije: [],
      prevoznici: [],
      korisnikId: 0,
      pageNo: 0,
      totalPages: 1,
      search: { destinacija: "", prevoznikId: -1, cenaKarte: "" }
    }
  }

  componentDidMount() {
    this.getData();

  }

  async getData() {
    await this.getLinije(0);
    await this.getPrevoznici();
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

  async getLinije(page) {
    let config = {
      params: {
        pageNo: page
      }
    }

    if (this.state.search.destinacija != "") {
      config.params.destinacija = this.state.search.destinacija;
    }

    if (this.state.search.prevoznikId != -1) {
      config.params.prevoznikId = this.state.search.prevoznikId;
    }

    if (this.state.search.cenaKarte != "") {
      config.params.cenaKarte = this.state.search.cenaKarte;
    }

    try {
      console.log(config);
      let result = await AutobuskaAxios.get("/linije", config);

      if (result && result.status === 200) {
        this.setState({
          pageNo: page,
          linije: result.data,
          totalPages: result.headers["total-pages"],
        });
      }
    } catch (error) {
      alert("Nije uspelo dobavljanje.");
    }
  }





  renderLinije() {
    return this.state.linije.map((linija, index) => {
      return (
        <tr key={linija.id}>
          <td>{linija.id}</td>
          <td>{linija.prevoznik.naziv}</td>
          <td>{linija.destinacija}</td>
          <td>{linija.brojMesta}</td>
          <td>{linija.vremePolaska}</td>
          <td>{linija.cenaKarte}</td>
          <td><button className="btn btn-primary" data-toggle="modal" data-target="#rezervisi" onClick={() => this.rezervisi(linija.id, 2)}>Rezervisi</button></td>
          <td><button className="btn btn-warning" onClick={() => this.goToEdit(linija.id)}>Izmeni</button></td>
          <td><button className="btn btn-danger" onClick={() => this.delete(linija.id)}>Obrisi</button></td>



        </tr>
      )
    })
  }

  goToAdd() {
    this.props.history.push('/linije/add');
  }

  goToEdit(linijaId) {
    this.props.history.push('/linije/edit/' + linijaId);
  }

  doSearch() {
    this.getLinije(0);
  }

  async rezervisi(id, korisnikId) {
    try {
      await AutobuskaAxios.post(`/linije/${id}/${korisnikId}/rezervisi`);
      this.getLinije(0);
      alert('Uspesno ste rezervisali vasu kartu!');
    } catch (error) {
      alert("Nije moguce rezervisati liniju!");
    }

  }



  deleteFromState(linijaId) {
    var linije = this.state.linije;
    linije.forEach((element, index) => {
      if (element.id === linijaId) {
        linije.splice(index, 1);
        this.setState({ linije: linije });
      }
    });
  }

  delete(linijaId) {
    AutobuskaAxios.delete('/linije/' + linijaId)
      .then(res => {
        // handle success
        console.log(res);
        alert('Linija je uspesno obrisana!');
        this.deleteFromState(linijaId); // ili refresh page-a window.location.reload();
      })
      .catch(error => {
        // handle error
        console.log(error);
        alert('Error occured please try again!');
      });
  }

  searchValueInputChange(event) {
    let control = event.target;

    let name = control.name;
    let value = control.value;

    let search = this.state.search;
    search[name] = value;

    this.setState({ search: search });

    this.doSearch()
  }



  render() {
    return (
      <div>
        <h1>LINIJE</h1>
        <div>
          <button className="btn btn-success" onClick={() => this.goToAdd()}>DODAJ LINIJU</button>

          <br /><br />

          <Form className="pretraga" style={{ marginTop: 50 }}>
            <Form.Group>
              <h1> Red voznje u medjumesnom i medjunarodnom saobracaju</h1>
              <Form.Label>Destinacija</Form.Label>
              <Form.Control
                value={this.state.search.destinacija}
                name="destinacija"
                as="input"
                onChange={(e) => this.searchValueInputChange(e)}
              ></Form.Control>
            </Form.Group>
            <Form.Group>
              <Form.Label>Prevoznik</Form.Label>
              <Form.Control
                onChange={(event) => this.searchValueInputChange(event)}
                name="prevoznikId"
                value={this.state.search.prevoznikId}
                as="select"
              >
                <option value={-1}></option>
                {this.state.prevoznici.map((prevoznik) => {
                  return (
                    <option value={prevoznik.id} key={prevoznik.id}>
                      {prevoznik.naziv}
                    </option>
                  );
                })}
              </Form.Control>
            </Form.Group>
            <Form.Group>
              <Form.Label>Maksimalna cena</Form.Label>
              <Form.Control
                value={this.state.search.cenaKarte}
                name="cenaKarte"
                as="input"
                onChange={(e) => this.searchValueInputChange(e)}
              ></Form.Control>
            </Form.Group>
          </Form><br></br>


          <Table className="table-hover table-dark visina " id="linije-table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Naziv prevoznika</th>
                <th>Destinacija</th>
                <th>Broj mesta</th>
                <th>Vreme polaska</th>
                <th>Cena Karte</th>
                <th></th>

              </tr>
            </thead>
            <tbody>
              {this.renderLinije()}
            </tbody>
          </Table>
          <div className="donjaMargina"><Button disabled={this.state.pageNo == 0} onClick={() => this.getLinije(this.state.pageNo - 1)}>Previous</Button>
            <Button className="margina " disabled={this.state.pageNo == this.state.totalPages - 1} onClick={() => this.getLinije(this.state.pageNo + 1)}>Next</Button></div>


          <div className="modal fade" id="rezervisi" tabIndex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div className="modal-dialog" role="document">
              <div className="modal-content">
                <div className="modal-header">
                  <h5 className="modal-title" id="exampleModalLabel">Modal title</h5>
                  <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div className="modal-body">
                  ...
                </div>
                <div className="modal-footer">
                  <button type="button" className="btn btn-secondary" data-dismiss="modal">Close</button>
                  <button type="button" className="btn btn-primary">Save changes</button>
                </div>
              </div>
            </div>
          </div>

        </div>
      </div>









    )
  }


}








export default Linije;