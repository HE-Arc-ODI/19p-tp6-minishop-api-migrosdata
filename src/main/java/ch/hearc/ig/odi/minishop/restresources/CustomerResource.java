/*
 * Copyright (c) 2018. Cours Outils de développement intégré, HEG Arc.
 */

package ch.hearc.ig.odi.minishop.restresources;

import ch.hearc.ig.odi.minishop.business.Customer;
import ch.hearc.ig.odi.minishop.exception.CustomerException;
import ch.hearc.ig.odi.minishop.services.PersistenceService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
    return persistenceService.getCustomerByID(customerId);
  }
}

