package com.tkartas.resources;

import com.tkartas.model.Profile;
import com.tkartas.service.ProfileService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/profiles")
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class ProfileResource {
    // == fields ==
    ProfileService profileService=new ProfileService();

    // == GET requests ==
    @GET
    public List<Profile> getProfiles(){
        return profileService.getAllProfiles();
    }

    @GET
    @Path("/{profileName}")
    public Profile getProfile(@PathParam("profileName") String profileName){
        return profileService.getProfile(profileName);
    }

    // == POST requests ==
    @POST
    public Profile addProfile(Profile Profile){
        return profileService.addProfile(Profile);
    }

    // == PUT requests ==
    @PUT
    @Path("/{profileName}")
    public Profile updateProfile(@PathParam("profileName") String profileName,Profile Profile){
        Profile.setProfileName(profileName);
        return profileService.updateProfile(Profile);
    }

    // == DELETE requests ==
    @DELETE
    @Path("/{profileName}")
    public void deleteProfile(@PathParam("profileName") String profileName){
        profileService.removeProfile(profileName);
    }
}
