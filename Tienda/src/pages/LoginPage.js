import React, { useContext, useState } from "react";
import {
  Button, Card, CardBody, Form, Input, InputGroupAddon, InputGroupText, InputGroup,
  Col, CardTitle, Row, CardHeader, Alert
} from "reactstrap";

import { Link, Redirect, useHistory } from "react-router-dom";
import { DataContext } from "store/GlobalState";
import { postData } from "utils/fetchData";
import Layout from "components/Layout/Layout";

function LoginPage() {

  const initialState = { username: '', password: '' };
  const [userData, setUserData] = useState(initialState);

  const [message, setMessage] = useState(''); 

  const { state, dispatch } = useContext(DataContext);

  const { auth } = state;

  const router = useHistory()

  const handleChangeInput = e => {
    const { name, value } = e.target;
    setUserData({ ...userData, [name]: value });
  }

  const handleSubmit = async e => {
    e.preventDefault();

    const response = await postData('auth/signin', userData);

    if (response.error) return setMessage('Usuario o contraseña incorrectos.');

    dispatch({
      type: 'AUTH', payload: {
        token: response.token,
        user: response.user
      }
    });

    localStorage.setItem('jwt', response.token);

    response.user.roles.includes("ROLE_USER") ? router.push("/") : router.push("/profile");

  }

  if (auth.user) return <Redirect to="/" />

  return (
    <Layout>
      <Row className="justify-content-center">
        {
          message && (
            <Col md="10">
              <Alert className="rounded" color="danger">{message}</Alert>
            </Col>
          )
        }
        <Col md="6">
          <Card className="card p-3">
            <Form className="form" onSubmit={handleSubmit}>
              <CardHeader className="text-center">
                <CardTitle className="title-up pt-2" tag="h3">
                  Login
                </CardTitle>
                <div className="social-line">
                  <Button
                    className="btn-neutral btn-icon btn-round"
                    color="facebook"
                  >
                    <i className="fab fa-facebook-square"></i>
                  </Button>
                  <Button
                    className="btn-neutral btn-icon btn-round"
                    color="twitter"
                    size="lg"
                  >
                    <i className="fab fa-twitter"></i>
                  </Button>
                  <Button
                    className="btn-neutral btn-icon btn-round"
                    color="google"
                  >
                    <i className="fab fa-google-plus"></i>
                  </Button>
                </div>
              </CardHeader>
              <CardBody>
                <Row>
                  <Col md="12">
                    <InputGroup>
                      <InputGroupAddon addonType="prepend">
                        <InputGroupText>
                          <i className="now-ui-icons users_circle-08"></i>
                        </InputGroupText>
                      </InputGroupAddon>
                      <Input
                        placeholder="Ingrese su username"
                        type="text"
                        name="username"
                        value={userData.username}
                        onChange={handleChangeInput}
                        required
                      ></Input>
                    </InputGroup>
                    <InputGroup>
                      <InputGroupAddon addonType="prepend">
                        <InputGroupText>
                          <i className="now-ui-icons text_caps-small"></i>
                        </InputGroupText>
                      </InputGroupAddon>
                      <Input
                        placeholder="Ingrese su contraseña"
                        type="password"
                        name="password"
                        value={userData.password}
                        onChange={handleChangeInput}
                        autoComplete="on"
                        required
                      ></Input>
                    </InputGroup>
                  </Col>
                </Row>
                <Button
                  className="btn-primary btn-round w-100" color="info" size="lg">
                  Login
                </Button>
                <h6 className="text-center mb-4">
                  <Link className="text-dark" to="/registro">
                    Registrarse
                  </Link>
                </h6>
              </CardBody>
            </Form>
          </Card>
        </Col>
      </Row>
    </Layout>
  );
}

export default LoginPage;
