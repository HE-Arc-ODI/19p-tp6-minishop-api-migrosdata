/*
 * Copyright (c) 2018. Cours Outils de développement intégré, HEG Arc.
 */

package ch.hearc.ig.odi.minishop.restresources;

import ch.hearc.ig.odi.minishop.business.Customer;
import ch.hearc.ig.odi.minishop.exception.CustomerException;
import ch.hearc.ig.odi.minishop.exception.NotFoundException;
import ch.hearc.ig.odi.minishop.exception.NullFormException;
import ch.hearc.ig.odi.minishop.services.PersistenceService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class CustomerResource {

  @Inject
  private PersistenceService persistenceService;

  @GET
  public List<Customer> getCustomers() {
    return persistenceService.getAllCustomers();
  }

  @GET
  @Path("{id}")
  public Customer getCustomer(@PathParam("id") Long customerId) throws CustomerException {
    try {
      return persistenceService.getCustomerByID(customerId);
    } catch (CustomerException e) {
      e.printStackTrace();
      throw new NotFoundException("the cart does not exist");
    }
  }

  @POST
  public Customer customerPost(
      @FormParam("username") String username,
      @FormParam("firstname") String firstname,
      @FormParam("lastname") String lastname,
      @FormParam("email") String email,
      @FormParam("phone") String phone) {
    return persistenceService.createAndPersistCustomer(username, firstname, lastname, email, phone);
  }

  @PUT
  @Path("{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Customer updateCustomer(@PathParam("id") Long id, Customer customer) {
    try {
      return persistenceService.updateCustomer(id, customer);
    } catch (CustomerException e) {
      e.printStackTrace();
      throw new NullFormException("customer couldn't have been updated.");
    }
  }
}

