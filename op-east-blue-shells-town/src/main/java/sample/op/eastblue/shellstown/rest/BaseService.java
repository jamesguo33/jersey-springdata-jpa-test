package sample.op.eastblue.shellstown.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import org.springframework.data.jpa.repository.JpaRepository;

import sample.op.eastblue.dawnisland.model.BaseEntity;



@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public abstract class BaseService<T extends BaseEntity, R extends JpaRepository<T, Long>> {
	
	R repo;
	
	public void setRepo(R r) {
		this.repo = r;
	}
	
	@POST
	public Response create(T entity) {
		if (entity == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		entity.setId(null);
		repo.save(entity);
		return Response.created(UriBuilder.fromPath("/" + entity.getId()).build()).build();
	}
	
	@GET
	@Path("/{id}")
	public Response retrieve(@PathParam("id") Long id) {
		T entity = repo.findOne(id);
		if (entity != null) {
			return Response.ok(entity).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}
	
	
//	@GET
//	public C retrieve(@QueryParam("page") int page, @Context UriInfo uriInfo) throws UnsupportedEncodingException, IllegalArgumentException, UriBuilderException {
//		Collection<T> entities = dao.retrieve(page);
//		for (T entity : entities) {
//			entity.createStandardLinks();
//		}
//		C collection = encapsulateEntities(entities);
//		long totalResults = dao.count();
//		int pageSize = dao.getPageSize();
//		
//		int totalPages = (int)(totalResults / pageSize);
//		
//		collection.addLink(buildPageLink(totalPages, "lastPage", uriInfo));
//		
//		if (page < totalPages) {
//			collection.addLink(buildPageLink(page + 1,  "nextPage", uriInfo));
//		}
//		
//		if (page > 0) {
//			collection.addLink(buildPageLink(page - 1, "prevPage", uriInfo));
//		}
//		
//		return collection;
//	}	
//	
//	
//	@PUT
//	public Response update(T entity) {
//		if (entity == null || entity.getId() == null) {
//			return Response.status(Status.BAD_REQUEST).build();
//		}
//		dao.update(entity);
//		return Response.ok(entity).build();
//	}
//	
//	
//	@SuppressWarnings("unchecked")
//	@DELETE
//	@Path("/{id}")
//	public Response delete(@PathParam("id")Long id) {
//		T entity = (T)retrieve(id).getEntity();
//		if (entity == null) {
//			return Response.status(Status.NOT_FOUND).build();
//		}
//		if (entity == null) {
//			return Response.status(Status.BAD_REQUEST).build();
//		}
//		dao.delete(entity);
//		return Response.ok().build();
//	}
//	
//	
	
}
