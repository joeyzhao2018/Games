package joeyzhao.game.revolvingRestaurant;

public interface RevolvingRestaurantMove {
	boolean isValid(RevolvingRestaurantState state);
    void make(RevolvingRestaurantState state);
    String toString();
}
