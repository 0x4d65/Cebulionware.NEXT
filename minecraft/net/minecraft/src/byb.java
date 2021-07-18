package net.minecraft.src;

public class byb
{
    public float[][] a = new float[6][4];
    public float[] b = new float[16];
    public float[] c = new float[16];
    public float[] d = new float[16];

    private double a(float[] p_a_1_, double p_a_2_, double p_a_4_, double p_a_6_)
    {
        return (double)p_a_1_[0] * p_a_2_ + (double)p_a_1_[1] * p_a_4_ + (double)p_a_1_[2] * p_a_6_ + (double)p_a_1_[3];
    }

    public boolean b(double p_b_1_, double p_b_3_, double p_b_5_, double p_b_7_, double p_b_9_, double p_b_11_)
    {
        for (int i = 0; i < 6; ++i)
        {
            float[] afloat = this.a[i];

            if (this.a(afloat, p_b_1_, p_b_3_, p_b_5_) <= 0.0D && this.a(afloat, p_b_7_, p_b_3_, p_b_5_) <= 0.0D && this.a(afloat, p_b_1_, p_b_9_, p_b_5_) <= 0.0D && this.a(afloat, p_b_7_, p_b_9_, p_b_5_) <= 0.0D && this.a(afloat, p_b_1_, p_b_3_, p_b_11_) <= 0.0D && this.a(afloat, p_b_7_, p_b_3_, p_b_11_) <= 0.0D && this.a(afloat, p_b_1_, p_b_9_, p_b_11_) <= 0.0D && this.a(afloat, p_b_7_, p_b_9_, p_b_11_) <= 0.0D)
            {
                return false;
            }
        }

        return true;
    }
}
