package dev.decagon.facebookclone.serviceimpl;


import dev.decagon.facebookclone.entity.Post;
import dev.decagon.facebookclone.entity.PostLikes;
import dev.decagon.facebookclone.entity.User;
import dev.decagon.facebookclone.repository.LikeRepository;
import dev.decagon.facebookclone.repository.PostRepository;
import dev.decagon.facebookclone.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {

    final
    private LikeRepository likeRepository;

    final
    private PostRepository postRepository;

    public LikeServiceImpl(LikeRepository likeRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
    }


    @Override
    public boolean likePost(User user, Long postId, String action) {
        boolean status = false;
        Post post = postRepository.findByPostId(postId);

        try{
            PostLikes like = new PostLikes();
            like.setUser(user);
            like.setPost(post);

            if(action.equals("1")){
                likeRepository.save(like);
                System.out.println("save like");
            }else{
                System.out.println("delete likes");
                likeRepository.deletePostLikesByPostAndUser(post, user);
            }
            status = true;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return status;
    }

    @Override
    public void deleteAllLikesInPost(Post post) {
        likeRepository.deleteAllByPost(post);
    }
}
