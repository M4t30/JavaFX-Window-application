package Products.model;

/**
 * Created by M4teo on 20.03.2017.
 */
public enum ProductType
{
    GPU,
    CPU,
    RAM;

    @Override
    public String toString()
    {
        switch (this)
        {
            case GPU:
                return "GPU";

            case CPU:
                return "CPU";

            case RAM:
                return "RAM";

            default:
                throw new IllegalArgumentException();
        }
    }
}
