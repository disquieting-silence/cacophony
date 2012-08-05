package dsq.cacophony.shame;

public interface Option<T> {
    <A> A fold(G<T, A> set, F<A> notSet);
    // FIX 4/08/12 Bad idea ...
    Option<T> setIfUnset(T value);
    T getOr(T value);
    Option<T> or(Option<T> value);
    <B> Option<B> bind(G<T, Option<B>> g);
}
