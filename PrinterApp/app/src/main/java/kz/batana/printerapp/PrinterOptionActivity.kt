package kz.batana.printerapp

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import com.RT_Printer.BluetoothPrinter.BLUETOOTH.BluetoothPrintDriver

class PrinterOptionActivity : Activity() {
    val baseImage = "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxETEhUQEhIWFRUVFRgXFhUYFRUYGBMVFRUXGBcVFxYYHSggGBolGxUWITEiJSorLi4uGB8zODMtNygtLisBCgoKDg0OGxAQGy0mICUtLS0rKy0tLS0tLS0tLS0tLS4vLy0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tL//AABEIAOYA2wMBEQACEQEDEQH/xAAcAAEAAQUBAQAAAAAAAAAAAAAABgIDBAUHAQj/xABCEAABAwEFBAgDBQYFBQEAAAABAAIDEQQFEiExBkFRYQcTIjJxgZGhUrHBQmKSotEjM0NygvAUFbLC4XODs8PxY//EABsBAQADAQEBAQAAAAAAAAAAAAABAwQCBQYH/8QANBEBAAIBAwICBwcEAwEAAAAAAAECAwQRMRIhQVEFEyIyYZGxQnGBocHh8BQj0fEGNHIz/9oADAMBAAIRAxEAPwDuKAgICAgICAgICAgICAgICCN7OTvE88D3F1HFwqSdHUJ8wWrDprTGS1JlmwzMXtWUkW5pEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBBF7Z+yvBj90oAPiQWU9Q0rBf2NTE+f+v8ADLb2c0T5pQt7UICAgICAgICAgICAgICAgICAg8c6iCy6UoNXarycyWgNQAKjjXPyOYQbiCUOaHN0KCtAQUTStaC5xDQNSTQDzUTaIjeUTMR3lHrftYxuUTS/7x7LfIan2WLJrax2rG7NfUxHuo5eN6yzOa55ALe7hFKaHXXcFiyZr5JiZ8Ga+S153lbdeM51mk/G79VzOW8/an5o67ecvWXlONJpPxuPsSkZckfan5nrLecs2zbS2lurg8cHNHzFCra6vLHjusjUXhu7BtXG7KVpYeI7Tf1HotePW1ntaNl9NTWfe7N/FK1wDmkOB0INQfNbImJjeGiJie8KLXaWRsL3mjRr+g4lRe8UjqktaKxvKOWAzWucTklkUbqtAOpG7mTvPksOPrz5Ovisfz/bLTqy26vCEpXoNYgICAgICAgICAg8caIMZzqoPEEankxOLuJJUDZ3DaKOMZ0OY8Rr7fJSN6gwb2vNkDMTsye60auP0HNVZs1cdd5V5MkUjeUEvK8pJnYnnLc0d1vgPqvIy5bZJ3swXyWvPdiAKpwuTWaRlMbHNrpiaRXwqurUtXmNkzWY5haXKBAQEGZd15SwuxRuy3tPdd4j66q3HltjnerumS1J7NxE99vlAccEbACWg58MuJPHcPfTE21N+/aI8F0TOe3fiEuhiaxoY0ANAoANwXpVrFY2hsiIiNoVqUiAgICAgICAgICCxM7OiC2gsW6TDG48qeuSCPKBesb6SMP3h6VzQSi1WhsbHSONA0VP6DmovaK1m0otaKxvLnN42500hkdv0G5rdwC8TJknJbql5l7zed5YqrctrszIxtoaX0AoQCdA4jL6+q0aWaxkjqW4JiLxulO1MjBZ3B9KmmEby6uRHgvQ1U1jHMT+DXnmOid0BXjvPEBAQEF2zWh0bg9ho4aH6cwuq2ms7wmtprO8OhXPeTZ4w8ZEZOb8Lv04L2sOWMld3o48kXjdnK1YICAgICAgICAgING+9aOcC2oDiKg7geBQX4rwjP2qeOXvogxr4lGFoBrU1y5f/UGqUC5AKuaPvD5oLu21s7kA39t3ho0etfQLBrsnFPxZNTfiqKLzmQQEBAQEBAQSW4tnGSxiWRzu1XCG0FADSpJHJb9Ppa3r1WacWCLV3lqb6u7qJTHWooHNO+hqM+dQVmz4vV36VOWnRbZc2fvDqZgSew7sv8Do7yPtVTp8vq7/AAnlOG/RZ0Je09IQEBAQEBAQEBBRLoUGnkulp7pI9wgxJLskGgDvA/qgjl5ue2Q95tKDeP71UC3HeEg318R9UGNft9hsD2kUc9pY2h4ihPLKq6rG8ubTtDcbNXbY3WWJskxbNhq4lxBq4k4e3kaAgZcFiyxgy3nv34/m7NNcV+Z7s20bJSaxyNcOYLfcVB9lRbQ2+zO7mdNPhLU2q6LRH3onU4gYh6trRZ7YMleYU2xXrzDBVLgQEBAQEG7ujaJ8LOrLA9orhzoW1zpWhqKrVh1VsdenbdfjzzSNtmuvG3PmeZH6nIAaADQD+96pyZJyW6pVXvN53liqty6LcFp6yzxuOtMJ8WnDXzpXzXt6e/XjiXpYrdVIlsFcsEBAQEBAQEEL2m6QoIKx2cCaQZE1/ZsPNw7x5D1C6iri14jhruj+8rTa55rRPIXBjA1rdGNMjqnC0ZVAZSuueqm0RCKTMzvKdLhY8c6gqd2aCNSOqSTvNfVQMaSxxn7IHhl8kES2ssbetgiaSS4moyyaS0V9j6KZv0UtbyU5Z2jdsl8889es9qkj7j3N8CQPMaFdVvavuzsmLTXiW2su1Nob3sLxzFD6t/RaaazJHPddXUXjnuzv8+ssv7+Ch40Dqf1CjvZW/wBTiv79f1/dZ67Hb3oP8nsU37mbCTo3FX8ru17p6jBk9y38/HueqxW92WJatlJ29wtePHCfQ5e6rtoskcd3FtNaOO7U2mwSx9+NzeZGX4hks1sd6+9Cm1LV5hjLhyICAgIJrsU+sLhwkPu1p/VepoZ/tz97bpp9mfvSBbWkQEBAQEFi22uOJjpZXBjGipcdB/zy3oOR7X7cS2qsUNY4NOD5R94jRv3R510FkV2U2vuiC6cOs9GFkwWPrN8sjneTaMHu13qq7crqR2SO9rxjs8T55D2WDQauJyDRzJIC7w4rZbxSvMmTJGOs2lze3dJc5rSCMMOWGrsVD9/TT7q9i3onHWvvTv8Al8v3ebHpC8292Nv5/OG/um8WWiJszNDkQdWuGrSvGzYrYrzWz0seSL16oZiqdoS+XrrfI/VsQwt8uz8y8qjW26cPT5smot2bReMyCAgICDLst5TR9yRwHCtR+E5Kyua9eJdVyWrxLbWba2YZPY145VafqPZaa668e9G66uptHMMn/MrBN+9iwE78NPzMz9V367T5Pejb+fB36zFb3oDs5Z5c4J/KrX09KEeaf0mO/wD87fqj1FLe7LX2nZi0t0DXj7pz9HUVNtHkjjurtp7w1U9new0e1zT94EfNZ7VtXmNlU1mOVpcoTXYtlIHOO+QnyDWj5gr1dFG2OZ+Lbpo9h5btq4mnDG0yU31o3yOdfRRfW0rO1Y3Lamscd1d37URSODXtMZOhJBb4V3einHrKWnaeyaaitp2ns3y2NAgILNstbImOlkcGsYKucdwHz8EHFNsNqZLbJvbC0/s4/wDe/i4+2g3k2xGyi1t0eUuQlB3i4LH1Nmhi3tjaD/NSrvclVTy0RG0It0r2mkEUVe/LiPMMYfq9q9X0RTfJa3lH1/0wekbbUiPi5Ra3aBe1lnwebSHQej6zFtlLj/Ekc4eAAZX1aV876RvE5do8I/d6+jrtj385SC2ylsbnNzcGnCOLtw9aLzrXrWN7cNNrRWN5Re7LAIm0rVxzc7if0zK8nUZ5zW3njwh5t7zaWYs7kQEBAQEBAQejigz7LfdoZpK4jg7tD3zHkrq6jJXiVlct48W2g2tdSksTXDfhNPymtfVaa66eLQtjUz9qFzrbtm1b1TvAs/09n1U76bJz2/L9nW+G/wAGvvW8mtYLLAT1Tahzt8hJJIqPs1Pn4a05c0RX1dOPqqyZIiOivDSrKpEEzua+f2LA4EkAivEAkD2AXq4M/wDbjdtxZfZjdIlsaRBxzpB2p/xUnURO/YRnUaSvGr+bRoPXeKWVjZTe26ILpwIM64rJ11ohi1D5Gg/y1q78oKTwmI3l3lUtDl/Staa2iKL4IsXnI4/Rg9V9B6Iptitbzn6f7eR6QtveI8o+v+nPxG6SQMb3nODW+JNB7rXlvEb2niGelZnaI8XYLPEyGJrBk1jQ0eAFPVfJ5cveb2e57OOvwhrLVaS88ANB/e9eNnzzln4MGXLN5+CwqFYgICAgICAgICAgICAgIK4oy4hrQSSaADeVMRMztBETPaHQbrutscTGOALgMzzJJPlmvaxYYpSKy9HHjitYiWxVy1Cuk3aDqIRZozSSYGpGrItHHxd3R/VwXVYcXtt2ciVikQEEs6MrJjtuOmUUbnebqMHs53oubcO6R3dbVa5xbb21Y7dO7cwhg/oYAR+LEvqdDXo01fm8LVT1Z5+TF2EsgdaDM7uwtxV++6rW/wC4+QWD0nnjHh7zy0aWI6+qeITG12kvPADQfU818Xnzzln4O8uWbz8GOqFYg8c6iD0ICAgICAgICAgICAgrjjLiGtBJJoANSVMRMztBEb9oTnZ+5BAMTqGQjM7mj4R+q9fT6eMcbzy9DDi6I3nluVpXKZZA1pc40DQSSdAAKklBwDaG9XWq0SWg6OPZHwsGTB6a8yVdEbM8zvLXIgQEHSuiayUjnm+J7WDwY3Ef9Y9FxdbjhPSd5XCx893pOZZHvGZlkc4DiXvJp6lfXX2xYoieIj6Q+dieq828/wBUsuW7+ojwVqScTjuxUpQch+vFfAekddbV5N+KxxH6z8WusbRszl56RBfslmdI7C3zO4DiVZixWyW2h1Sk3naG5GyuLttkIO4OFR6jRbraGNvZlqnS+UsO0XBaG/ZxDi019jQ+yzW0mWvhv9ym2C8eDXSRuaaOBaeBBB9Cs8xMdpUzExyoUAgICAgICAgIKo2FxDWgkk0AGpKmImZ2giN+0J1s/cggGN1DIRmdzR8I+pXrafTxjjeeW/Dh6O88tytS8QRHpOvPqrGYwe1O7q/6NXnwoMP9S6rHdxeezjasUiAgIO0bCWTq7DCN7mmQ/wDccXD8pb6Ku3K+nDN2ktPV2SeQaiJ9P5i0hvuQrdLTrzUr8YcZ7dOO0/CXELrFbTE2laGtPAE18qVXtel5n+lybeTxMMd03X561CCmWQNFSpiNxvblvWyUDA/BxLxSvMkVHuvVw5MNK7RO33t2O+OsbQlkMjXCrCHDi0gj1C1RMT3hfExPCtSlTJG1wo4AjgQCPQqJiJ7SiYieWutFwWd32MJ4tNPbT2We2kxW8NvuVWwUnwau0bLH+HIDycKe4/RZ76Gfsz81NtLPhLV2i5rQzWMkcW9r5ZrNfTZa8x8lNsN48GCRuOqoVvEBAQEFUbC4hrQSSaADUlTETM7QRG/aE52fuQQjG+hkI8mDgPqV62n08Y43nlvw4ejvPLdLUvEBByPpYt2O1MhByij9HSGp/KGKynCnJPdCV04EBB4SpE9unba0RsYxzGPa1oAyLSAAABUZaclTLRHCvava+O0WR0LWPY97mVrQto1wce0M/sjcvQ9F06tRv5RM/p+rJr7bYdvOY/yj/R/ZWvnkmJHYbhaKipLtSByaKf1LT6Uy+zFPP6Qo0OPvNp8EstN3uBqzMcN4XyOXR2id6cLsmnmJ3q0N43xFESwkl4NMAGdeFdBquaaPLbmNo85VRht4q3vJ1WdwyI4RTMZrmZQqYwtNWOc08QSD6jNTFpjhMWmOGxs9+2tn2w8cHivvkfdaK6vJHj81tc94bSz7Xj+LCRzaa+xp81prrY+1HyXV1MeMNtZb+s0mkoB4O7P+rIrRXUY7cSurlpPi2QNcxorlggtT2Zj8nsa7xAK5tStvejdzNYtzDWWjZuB3dxMPI1Ho6qzW0eOeOyq2npPHZq7RsvIO49ruRq0/Ue6zW0N492d1NtNbwlrLTdszO9G4DjSo9Rks9sOSvMKbY7V5hjRsLiGtFSTQAak8FXEbztDiO/Cc7P3GIRjfQyEeTBwHPiV62n08Y43nlvw4ejvPLdLUvEBAQcE2vtPWW20v/wD1c3yj7A9mq2OFFuWpClyreBRErRKCklSM6C2toA7KgpyVc1lbFoXJnsc05g+Bzqu8Oa+C3VVzkx0y16bMCNgGijLltlt1WdY8dccbVbCC/rREKtldQbnHEPCjq08lXEbupnZgXe4y2lrnmpfIXk8XZu+ajUz04bTHkoyTtWZTaOCmZXz8yw7rygEBAQUujB3KdxVA58eccjmeBIHoNV3XJavEuovMcNnZ9pLUzvYZBzFD6tp8itFNZeOe66uotHLaWba+I5SRvYeI7Q+h9lprrazzC2uprPLb2S9YJO5K0nhWh/CaFaK5aW4ldXJW3EsxWOxBQ2BmLGGtxfFQVz5rnorvvt3R0xvvszl0kQEBAQfOdtkxSSP+J7nerifqrmeVlEPCUFBKlKklEtpdNyumpuB04041OgXi+kPTNNNM0rG8xz5R8PjL1NJ6N9bT1mSdo8POWztOxrgKsfU+R+gXmYv+TTv/AHKRt8O3+Wi3ovFPu2mPv2n6bI1a4pInYXZfIr6TTajFqadeP94eZnwZMFum/wC0saSUnVaIiIUTO5Z5yx7Xt1aQR5blF6Res1nxRMbxs6Dd9vZMzGw+I3tPAhfPZcNsVumzDas1naWSqnIgICAgICAgodC07lO4v2a1Tx/u5nNHCtR6HL2Vlc168S7rktXiWzs+1Nob32NeOI7J9qj2WmuttHMbrq6m3iyrl29sk9rFhwyMmJIALQWOLYzIaOB3AHUDReljnrpF2uluqu6aKXQgICAg+bnanxVzMpJUpUEoKSUS9iZicG8SB6miry39XS1/KJn5O8VOu8U85iPm6FcDAMdN2EDkM1+a6u02mJnx3fYZoiIiI4bhZFCDbaYTVw3SAD8JB9x7L6v/AI7a0ZJr51/WNvqy+lKx/T1med/ruiJK+ufPqSUS8bIWmrSQeIJB9QkxExtJtum2zLLSW45pHFpHYY4An+YmlfALxNZOGLdOOO/jLHl6N9qw3ixKRAQEBAQEBAQePcACToBU+A1UxG/YQbo2nMl9WaQ6vlmeeWKCY0919LFejH0+UQ9WI2iIfR6qSICAgIPnW9Y8E8rPhlkb+F5H0V0M88sMlSKSUSpJQXbvP7Vn8w+ay67/AK2T/wAz9GnR/wDYp98J7cclHlvxD3H/AASvzzU13ru+szR23Zt5W4MGEHtH8vNU4cXVO88K8ePq7zwgG0tpqWxjd2j4nT2r6r7X0Fp+mlss+PaPujn8/o8r0xmibVxR4d5/T8vq0ZK994qglEpTs5s9Wk0w5tYfZzh9F5ms1m3sU/GWbLl+zVLF5LM8QUTSta0ucaAaldVrNp6a8piJmdoRa33w976sLmgd0Amp5mmp5L2sOkpjrtaImfFuphrWO6QXW2UMrK6pOgoOyOZGpXl6icU2/tx2ZMk039lmLOrEBAQEGm2wtgjssmecg6tvPHkfy4itWjx9eaPh3/n4rcFeq8NL0NwYr1iPwRyv/Jg/9gXu34ek+iFSgQEBAQcH28svV2+0Npk5+Mc+saHE+pPora8KbR3R8ldIUkoKCUSqgkwva7g4H0KrzU68dqecTHzhZhv0ZK28pifzTJriDUGhG9fn0xvy+2mN2NeNtEbS92Z3De5y16LSW1OSKV48Z8o/nDNqtRXT4+qfwjzlDZpS4lxNSTU+K+4x0rjrFK8Q+Sveb2m1uZWiV25SDZe6g6THIK4RiDTx3V+fksetzTjx9uZ7Ks1umvZNF4TEIKJpWsaXONANSuq1m09NeSImZ2hDr3vUyu4MGg+p5/Je5ptNGKPi9DFiikfFurhujABLIO2c2j4BxP3vksOr1fX7FOPH4/sz5s3V7McN0vPZxBRPM1jS95DWtFSToAuq1m07RyREzO0OeXztbNJJWB7o2N7oyq77zgfluXtYNDStfbjeW/Hp6xHtd5XLHtvaG5SMZIPNjvUZey5v6Oxz7szH5/z5otpqzx2bJ+3jKZQOxcC5oHrSvsqI9G2372hX/Sz5orfF7y2l+OQigya0d1oPDnxK9HDgphrtVpx44pG0OhdAdhxWm02in7uFsYP/AFX4j/4R6rrJPZ27aqgQEBAQcq6YrBhlhtIGT2GNx5sOJvmQ534VZRXeHOSV24UkolSSgoJQSWz3rGIWvc7MChb9ouGWnPXzXyOf0ZmtqrUpHaZ338Np/wAcPqMPpDFGni9578beO8I9eFtdK7E7yG5o/vevpdJpKabH0U/GfN4Op1N9Rfqt+EeTEJWpnbCxWWnadruHDmeahLe3HawySjjQOFK8Du/TzWLW4pvj3jwU56dVe3glC8RiUSyBoLnGgGZJ3LqtZtO0ckRMztCFX3e5mdQZMboOJ+Ir3NNpowx8XoYsUUj4sO73t66IO0MjR41cMlbmmYx2mPKXeT3Z2dEXzjzHiCieZrGl7yGtaKknQAb1Nazado5IiZnaHNdp9oXWl2FtWwtPZbvefjd9Bu8V72l0sYY3n3no4cMUjeeWjWtcICAg7/0J3X1V39aRR1okdJn8DaRs8iGF39apvPdCfrgEBAQEEd2+ug2mxSMaKvZ+0j4lzKmg5luJvmuqztKLRvDglVapUkol7Z4i97WClXEAV0zRL28LK6J5jcQSKZjTMV+qQMUlSlSSgzbJZ6dp2u4cOfioSyi9QlSXqRsLJeszB3+yNAQDTzOdFmvpMV53mFdsNJ77Nded7SS5FxwjdoDzoFZiwUx+7DqtK14hrJJgBUq12wHzOrirQg1FNxGYop237GzpNxbQRzMGMhkm8E0Dj8TSdx4a/NeFqNJfHPsxvH85YMmG1Z7cNlabbFG0ue9rQN5I9hvWemK952rCutLW7RDnW0+0LrS7A2rYWnIb3kfad9Bu8V7Wl0sYY3n3vo34cMUjeeWiWxcICAgybtsD55o7PH35XtY3KtC40xHkBUnkCnA+rLusTIYo4IxRkTGsaODWNDR7BZpQyUBAQEBAQcD6QLi/wlre1opFLWSLgAT2mf0uypwLeKurO8KpjaUZJUiklBS5yJUEqRk2aGnaOu4cFCWQXolSXoKmcTogxbVa8WQ0+aJYj5aKRhyPJzKJXrNBXtHTcOP/AAgzC+mqga602jFkMh810lZQEHgKD1AQdV6DNnMcj7xeOzHWKGu97h+0ePBpw1+88blXknwQ7SqgQEBAQEBBHNu9nBbbMWNp1rO3EdO1TNhPBwy9DuXVZ2lExu+f5GlpLXAggkEHIgg0II3EEK1WtkqUqSUF6GOmZUJXS9BSXokDt5QYtotNchp81KWM6RBZe6qJVwxVzOnDigyi9BhTzYstyJWlIIPWnMGlc9OPJBu7/vaKZjGsaQQa1IAwihGEe3ouYgaNdDYbP3NLbLRHZYe9Ic3UqI2DvSO5AepoNSFEztG4+obmuyOzQR2aIUZG0NbxPFxO8k1JPElUTO6GaoBAQEBAQEBBy7pW2OLsV4WduYFZ2DeB/FA4gd7kK7jWylvBzMOTEqxyuRtpmUSqL1ApL0SpL0GNNOT4KUrJcgoJRL2Nm8oLxeiViWWvgpFtAQEBAQVRRuc4Ma0uc4hrWgVLnE0DQBqSckH0T0Z7Fi74C+ShtMoBkIzEbdRE08BvO88gKUWtuhM1yCAgICAgICAgIOP9IvR+Yi62WRlY+9JC0ZxcXsA1ZxH2d2XdsrbwlzMObY1YhSXolQXoLMj0StkoKCUSy7uEeeOnKunNBZlcKmmlTTwRKy99VIpQEBAQEFUUbnODGtLnOIDWtBLnE6AAZkngg7t0Y9HYsYFrtQDrSR2G5EWYEZgHQyEGhcNMwN5dTa+/aEOjLgEBAQEBAQEBAQEBBzPbroybLitFhAZIc3QnJkh3lh0Y7l3TyzJ7rfzRs47a4JI3ujkY5j2mjmOBDgeYP9lWoWC5EqCUFBKJUkqR5VElUBAQEBAQbG4bjtNsl6mzRF7sqnRkYP2nv0aPc0yBOSiZiOR3rYPo+gu8da4iW0kZykZRg6tiH2RxdqeQyFNrboTNcggICAgICAgICAgICAg020my9ktrcNoiBIFGyDsyM/leN3I1B3hTEzA5DtN0UWyCr7Mf8THwFGytHNuj/FpqfhVkXieRz6eNzHFj2uY8asc0tc3xacx5rsWiVI8RIgICAgIMq7LtntD+qs8T5X/CxpNK6Fx0aOZICT25HT9luhx7qSW+TCNeoiNXHk+XQeDa8nKucnkh1m6rrgs0Yhs8TY2D7LRSp3knVxPE5lVzO4zFAICAgICAgICAgICAgICAgINfe1yWW0tw2iCOUDTGwEt/ldq0+CmJmOBCL16G7A/OCSaA7gHdYzzElXfmC6jJIid49DNsYCYrTBIB8Yki9gHrv1kCHXtsxPZ69Y6M0+Fzj82hTFolLTtjJNF0JHdOw9rtFOrdCK/E+QfKMrnqgTC7+hSc0M9rjYN4jjc8nwc4tp6Fc+sQlt0dE12Q0MjZLQ4b5X9n8DMLSORBXM3kTWxWKKFgjhjZGwaMY0NaPANFFwL6AgICAgICAgICAgIP/9k="
    val baseImage2 = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAkAAAABfAQAAAAAo/kX8AAAAAmJLR0QAAd2KE6QAAARySURBVFjD7di9b+M2FADwR9MwVcCQFHRIhgByuly3y3Uuqn+dhosmwwHJeNvxmqJjq/YWFlDJvvdIy9bFNo9KlgLWkNiC/DO/3nukwb/SBWfoDJ2hM/RSqFOdty8QuipCRrwMMipCqruDRTPZcSAjdLV8D5AF/XELoLZv7ADdbGpYPuRAgBd9mjUAEW66n8UK4E1WZwLkCLI7SEMm1O9DGl+3DOFtfPNtaDDel9xvGrRjS8zuQ/S6OQAF3wBU+MVVGuLX1QD5bg+qfPjb7WbmOGToNT8H/wjQut6DQnsb7KVIQ/iNYQYJWo0hgU/ESU5CDp+O08bQx0eEuKeSxrgPbfU6CVkcgviFzyHtLU2Dk75rU5ABXgCHIeMtAa4K4BFIMaTp+fII1LUdLztcAMeg0FKEaIptw+8Z+lDvQTZCDTXqBITAHHMHGe+20OPtCDL8XJuAtIACaJA8rFSENnAQ8qegELohlmgBMPR+H+oDRP1TJyAO3RJi4G7HqFPZUEfQj7wONAUoQ3/D22yIoyxkI44ohn4rHp6NUQqiRATULB6tKkBGNNkQjzU1i0dLBeheNbsp6n3isphSBUOKmtVYjhiEvte6b1OfHkE4Mjz7FefHjlMGQq3WOsPZg9QAQUxsWZWWoX6AWiMiZLSW2V3DuqhhQfOG81cS9C8stQkj9vySJ6DGYdBWRnzW+Bco+c8QejAHIXEMkrgGeoQaLZ9CGUFI6A7e5ELYJYtQC3IdIaeF/pBVaQlSCHWwxB5VmE0iBPqeoj8bMrgcHTRbyK9A/5INNUYgVODA/DRAV6DXXR7UYvALv0bIgkWoYWhzHwpkNiQR6igBRMhg7d+H3NdB0ilNORuhOkAYIb+OqwhpVSKNIFQhpOUOuo6biFyobxCi6dtCqy+gniCfhISNkIiQ0/OJUEtQRVDLUFEcgJJd63Cbr8OCQshhrFCsTRkjMB5DJGQjjF+CFoegJtUiWDNElRtavMX5aBK0CRCVbboVMmQ92tZ42oskIAuqx8yI1QMzpTecauUYiluIUxBfuIIQ6hkKObsaQ1xRuhQ0H8qRMtsq4kZQ2NJqn4AWA1QNENa1+nKADKVX1BLQbAcNBdKP99mSNhoiBYldpR1K9mIMVQTJFARDr9oBwqB93J2OcG4N7S5SkN5CvK1REVpdDpDgPVTL5+VT0Iogu4Voo8X6pf/6K0A1QT11quezGR2zMGHm1rUw2pWjTvV8GonQ22mQp071YSNJPbyYf8yA+rC/wLCn0yG+L/mw6Db+ryLv3H/wAn8L8ves/ZH/8zB0BcvPZY5jOYjoeHgXT2KuuaNK6z/1WT/+IIRT1TqBHzUM9ZXiSiudzIIuChDzm1LArDY1rItVgDDXHTkGHYNqnrayxqS0wdWMe5JGTflFy9ac1kryNpyMXCUnQdgxDfPyFgviE0eL/WY2DcL+zLCEFBq4QRfXGGwTIbyWsuDjkZgOXRTmBr67VsvZO4vLGcT1D5OgLy7pP9G/14CeXgeK1xn6P0L/AUg3ExgvCKWfAAAAAElFTkSuQmCC"
    val baseIamge3 = "data:image/png;base64,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"
    val baseImage4 = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEIAAABJCAIAAAAG8Wd8AAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAHlSURBVGhD7c/RlsMgCEXR/v9Pd4wcs2yMaJpimRn2UwTE3MfzT4gYnkQMTyKGJxHDk4jhyT+I8XCDH+qLGAvxQ31TMTgvx/MRY8OOiHETz0eMDTsuxuBOB0NzuLMyBqPTuKZidEEMJm5g0RkmrGPQbtBu0G7QbtC2i0GjQmMa1yo0KjSMYlAtqL6FFQXVgqpFDEoZJdXMmGwTlDJKH4/BOaOkYvTdJJztYnBWMVpQ7WMuO1TkqNAm2NEsTaSiY7SgqmJ0TQw51mhcx/2Cql0MvjLp7qh2zAzsKGX1UboKbYIdo400OmYGalRHjx5oE+yY20i7QqOg+opehcbcozttgh1XNjKUUSqoZpTOMHHl0cRg4whzHQxZx0ike4qJEabPMGEUoz2eYmKE6QbtKy+KqX86HBOpHNCr0FBbNXrNi3JUaBPsaJYmUmnp3UQZkJY4VOSoGD+ZcD576VNYmlGyi5FQyijdxrqMUkbJIkZCtaD6FlYUVAuqRjESGhUa07hWoVGhYRcjodeg3cFQg/YreqYxBBM3sOgMEwti7Bi9gpt9zK2MseNOB0NzuPOVGB/E8xFjw46IcRPPR4wNOxzgh/oixkL8UN944leIGJ5EDE8ihicRw5OI4UnE8CRieBIx/Hg+fwD2WlMmjn4oCAAAAABJRU5ErkJggg=="
    val baseImage5 = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA8AAAAPCAIAAAC0tAIdAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAABiSURBVChTtZBBDsAgCASh//8z3XQ3pq7V6KFzMciAYFZVbHPp3OPM1iSZyXiGtGYvFmjZj0mQI4pfuA0JbchY0NlUFTyzWsGfP9jZ9rQNBrw3C4ipQFfIMZ4hbWyw4GTLiBsFwzYUSi2LkwAAAABJRU5ErkJggg=="
    val baseImage6 = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAAAtCAIAAABks7P4AAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAALuSURBVHhe7ZLZdsMwCET7/z+dqjAG1ZFlLdZq7ktOWGdkfj6G0QA7LKMJdlhGE/SwfgzjCXBO/OOQkGEUY4dlNMEOy2iCHZbRhHcdlrM2D9C0KWJw1cOib5QKeuYAmpJB2yKI4EkPi540BupeAAxfg7o5ED3jD4se5wxykzGnNlb1DdJ9kb26voMU8nsGuekRqatoptf9BxItkS26rMVisqMguiAn8Yt6oY+gIPooMlanP7KJBCuILk7QyAbu6BMpiNYhc3RczWgS9gf+70LcVDy7HGzHgf9FSLtOyZ1IGgBCe5Hoaz/79EkBQslIi3amT6GNu73miSyDG78GfeqSp9CelH7asu0jCgUe934W+uxJBqVMq+OdNHnnt2NqbNb0LkGKQSnQukjP7bh2/FnpCLZWgEG9wNaOxJdKVouuGoao93ECfBB9JXiCA0S7E1ktKa0IVg9Uf4WT5IPojsChBxITcCVG4poOlk5lJohTeAKJBYGBA0Sn5EqexDUdLJ3cXhCn2QfRKYHEA0RX4EqtxDV9W7ooTr8PooOAiANEVyOiXFJakVK9Ac7LEDuj9j5O3IVktei2IV6wCgNdrP6AdAI3FqRA6257HDR54dcZLn7R16PPnqRcyrQ6sdNBW9Z7oEk0r/V09KkzBEux9mT1O2gjQGhiakSyx2+QzqemtwPsjkEoGWnRzoIpAmkACM1Elip24YPEF0h7IJFAbn0HWJID/4uQdp1SOVEgbQChcaTI4BoB0SIwwgOJC24LmsIKBUTrkDk67qnRPiRYQbQXwY2sxAeJNmDHAaIewWAjWIOA6KPIWJ3eaJMP2TmD3NPwZF7hw9lRQIQHBzn7IDz8BHItkS26rM/ib8hyAKSLwIhBjrKA0FKpaP4C6e7IalUwUE0Qep9LUPQCYPgCFE2DSJr3sOLQq+aBznFARyZoXgQRvOphFUCfaSTQsTVi80WHZXTADstogh2W0YTwYRlGPTgn/jGMZ7HDMhrw+fwCMku6U23P9zIAAAAASUVORK5CYII="

    fun decodeBase64(input: String): Bitmap {
        val base64Image = input.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
        val decodedString = Base64.decode(base64Image, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }



    private var mBtnBack: Button? = null
    private var mBtnPrintText: Button? = null
    private var mBtnPrintImage: Button? = null
    private var mBtnPrint1DBarcode: Button? = null
    private var mBtnPrintTicket: Button? = null
    private var mBtnPrintTable: Button? = null
    private var m1DBarcodeContent: EditText? = null

    internal var mBtnBackOnClickListener: OnClickListener = OnClickListener { finish() }

    internal var mBtnPrintTextOnClickListener: OnClickListener = OnClickListener {
        if (BluetoothPrintDriver.IsNoConnection()) {
            return@OnClickListener
        }
        BluetoothPrintDriver.Begin()
        val tmpString = this@PrinterOptionActivity.resources.getString(R.string.print_text_content)
        BluetoothPrintDriver.ImportData(tmpString)
        BluetoothPrintDriver.ImportData("\r")
        BluetoothPrintDriver.LF()
        BluetoothPrintDriver.LF()
        BluetoothPrintDriver.excute()
        BluetoothPrintDriver.ClearData()
    }

    internal var mBtnPrintImageOnClickListener: OnClickListener = OnClickListener {
        //        if (BluetoothPrintDriver.IsNoConnection()) {
//            return@OnClickListener
//        }
//        var `in`: InputStream? = null
//        try {
//            `in` = resources.assets.open("Rongta.jpg")
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//        BufferedInputStream bis = new BufferedInputStream(in);
        //BluetoothPrintDriver.printImage()
        val bitmap = decodeBase64(baseImage6)
        //���ʹ�ӡͼƬǰ��ָ��


//        val start = byteArrayOf(0x1d, 0x4c, 0x1f, 0x00)
//        BluetoothPrintDriver.Begin()
//        BluetoothPrintDriver.ImportData(start, start.size)
//        BluetoothPrintDriver.excute()
//        BluetoothPrintDriver.ClearData()

        val ba = byteArrayOf(0x0A, 0x0, 0x1d, 0x2f, 0x31, 0xA)

        val byteImage = Utils.getReadBitMapBytes(bitmap)
        val formats = byteArrayOf(0x1B.toByte(), 0x58.toByte(), 0x31.toByte(), 0x24.toByte(), 0x2D.toByte())




//        BluetoothPrintDriver.ClearData()
//        val end = byteArrayOf(0x1d, 0x4c, 0x1f, 0x00)
//        BluetoothPrintDriver.ImportData(end, end.size)
//        BluetoothPrintDriver.excute()
//        BluetoothPrintDriver.ClearData()

    }

    internal var mBtnPrint1DBarcodeOnClickListener: OnClickListener = OnClickListener {
        if (BluetoothPrintDriver.IsNoConnection()) {
            return@OnClickListener
        }
        BluetoothPrintDriver.Begin()
        val print1DBarcodeStr = m1DBarcodeContent!!.text.toString()
        val len = print1DBarcodeStr.length
        if (len > 16) {
            val tmpString = this@PrinterOptionActivity.resources.getString(R.string.barcode_input_hint)
            Utils.ShowMessage(this@PrinterOptionActivity, tmpString)
            return@OnClickListener
        }
        //        	for(int i=0; i<len; i++){
        //        		if(print1DBarcodeStr.charAt(i)<'0' || print1DBarcodeStr.charAt(i)>'9'){
        //        			//Utils.ShowMessage(PrinterOptionActivity.this, "�����ַ�ֻ����0��9λ֮�������!");
        //        			String tmpString = PrinterOptionActivity.this.getResources().getString(R.string.barcode_input_hint);
        //        			Utils.ShowMessage(PrinterOptionActivity.this, tmpString);
        //            		return;
        //            	}
        //        	}
        BluetoothPrintDriver.AddCodePrint(BluetoothPrintDriver.Code128_B, print1DBarcodeStr)
        BluetoothPrintDriver.excute()
        BluetoothPrintDriver.ClearData()
    }

    internal var mBtnPrintTicketOnClickListener: OnClickListener = OnClickListener {
        if (BluetoothPrintDriver.IsNoConnection()) {
            return@OnClickListener
        }
        val tmpString1 = this@PrinterOptionActivity.resources.getString(R.string.print_smallticket_content1)
        val tmpString2 = this@PrinterOptionActivity.resources.getString(R.string.print_smallticket_content2)
        val tmpString3 = this@PrinterOptionActivity.resources.getString(R.string.print_smallticket_content3)
        val tmpString4 = this@PrinterOptionActivity.resources.getString(R.string.print_smallticket_content4)
        val tmpString5 = this@PrinterOptionActivity.resources.getString(R.string.print_smallticket_content5)
        val tmpString6 = this@PrinterOptionActivity.resources.getString(R.string.print_smallticket_content6)
        val tmpString7 = this@PrinterOptionActivity.resources.getString(R.string.print_smallticket_content7)
        val tmpString8 = this@PrinterOptionActivity.resources.getString(R.string.print_smallticket_content8)
        val tmpString9 = this@PrinterOptionActivity.resources.getString(R.string.print_smallticket_content9)
        val tmpString10 = this@PrinterOptionActivity.resources.getString(R.string.print_smallticket_content10)
        val tmpString11 = this@PrinterOptionActivity.resources.getString(R.string.print_smallticket_content11)


        BluetoothPrintDriver.Begin()
        BluetoothPrintDriver.LF()
        BluetoothPrintDriver.excute()
        BluetoothPrintDriver.ClearData()
        BluetoothPrintDriver.LF()
        BluetoothPrintDriver.excute()
        BluetoothPrintDriver.ClearData()
        BluetoothPrintDriver.AddAlignMode(1.toByte())//����
        BluetoothPrintDriver.SetLineSpace(50.toByte())
        BluetoothPrintDriver.SetZoom(0x11.toByte())//���ߣ�����
        BluetoothPrintDriver.ImportData(tmpString1)
        BluetoothPrintDriver.excute()
        BluetoothPrintDriver.ClearData()
        BluetoothPrintDriver.LF()
        BluetoothPrintDriver.excute()
        BluetoothPrintDriver.ClearData()
        BluetoothPrintDriver.LF()
        BluetoothPrintDriver.excute()
        BluetoothPrintDriver.ClearData()
        BluetoothPrintDriver.LF()
        BluetoothPrintDriver.excute()
        BluetoothPrintDriver.ClearData()
        BluetoothPrintDriver.AddAlignMode(0.toByte())//�����
        BluetoothPrintDriver.SetZoom(0x00.toByte())//Ĭ�Ͽ�ȡ�Ĭ�ϸ߶�
        BluetoothPrintDriver.ImportData(tmpString2)
        BluetoothPrintDriver.LF()
        BluetoothPrintDriver.excute()
        BluetoothPrintDriver.ClearData()
        BluetoothPrintDriver.ImportData(tmpString3)
        BluetoothPrintDriver.LF()
        BluetoothPrintDriver.excute()
        BluetoothPrintDriver.ClearData()
        BluetoothPrintDriver.ImportData(tmpString4)
        BluetoothPrintDriver.LF()
        BluetoothPrintDriver.excute()
        BluetoothPrintDriver.ClearData()
        BluetoothPrintDriver.ImportData("��������������������������������")
        BluetoothPrintDriver.LF()
        BluetoothPrintDriver.excute()
        BluetoothPrintDriver.ClearData()
        BluetoothPrintDriver.ImportData(tmpString5)
        BluetoothPrintDriver.LF()
        BluetoothPrintDriver.excute()
        BluetoothPrintDriver.ClearData()
        BluetoothPrintDriver.ImportData("��������������������������������")
        BluetoothPrintDriver.LF()
        BluetoothPrintDriver.excute()
        BluetoothPrintDriver.ClearData()
        BluetoothPrintDriver.ImportData(tmpString6)
        BluetoothPrintDriver.LF()
        BluetoothPrintDriver.excute()
        BluetoothPrintDriver.ClearData()
        BluetoothPrintDriver.ImportData(tmpString7)
        BluetoothPrintDriver.LF()
        BluetoothPrintDriver.excute()
        BluetoothPrintDriver.ClearData()
        BluetoothPrintDriver.ImportData("��������������������������������")
        BluetoothPrintDriver.LF()
        BluetoothPrintDriver.excute()
        BluetoothPrintDriver.ClearData()
        BluetoothPrintDriver.ImportData(tmpString8)
        BluetoothPrintDriver.LF()
        BluetoothPrintDriver.excute()
        BluetoothPrintDriver.ClearData()
        BluetoothPrintDriver.SetZoom(0x11.toByte())//���ߣ�����
        BluetoothPrintDriver.ImportData(tmpString9)
        BluetoothPrintDriver.LF()
        BluetoothPrintDriver.excute()
        BluetoothPrintDriver.ClearData()
        BluetoothPrintDriver.ImportData("����������")
        BluetoothPrintDriver.LF()
        BluetoothPrintDriver.excute()
        BluetoothPrintDriver.ClearData()
        BluetoothPrintDriver.ImportData(tmpString10)
        BluetoothPrintDriver.LF()
        BluetoothPrintDriver.excute()
        BluetoothPrintDriver.ClearData()
        BluetoothPrintDriver.SetZoom(0x00.toByte())//Ĭ�Ͽ�ȡ�Ĭ�ϸ߶�
        BluetoothPrintDriver.ImportData(tmpString11)
        BluetoothPrintDriver.LF()
        BluetoothPrintDriver.excute()
        BluetoothPrintDriver.ClearData()
        BluetoothPrintDriver.LF()
        BluetoothPrintDriver.excute()
        BluetoothPrintDriver.ClearData()
        BluetoothPrintDriver.LF()
        BluetoothPrintDriver.excute()
        BluetoothPrintDriver.ClearData()
        BluetoothPrintDriver.LF()
        BluetoothPrintDriver.excute()
        BluetoothPrintDriver.ClearData()
        BluetoothPrintDriver.LF()
        BluetoothPrintDriver.excute()
        BluetoothPrintDriver.ClearData()
    }

    internal var mBtnPrintTableOnClickListener: OnClickListener = OnClickListener {
        if (BluetoothPrintDriver.IsNoConnection()) {
            return@OnClickListener
        }
        val tmpString1 = this@PrinterOptionActivity.resources.getString(R.string.print_table_content1)
        val tmpString2 = this@PrinterOptionActivity.resources.getString(R.string.print_table_content2)
        val tmpString3 = this@PrinterOptionActivity.resources.getString(R.string.print_table_content3)
        val tmpString4 = this@PrinterOptionActivity.resources.getString(R.string.print_table_content4)
        val tmpString5 = this@PrinterOptionActivity.resources.getString(R.string.print_table_content5)
        val tmpString6 = this@PrinterOptionActivity.resources.getString(R.string.print_table_content6)
        val tmpString7 = this@PrinterOptionActivity.resources.getString(R.string.print_table_content7)
        val tmpString8 = this@PrinterOptionActivity.resources.getString(R.string.print_table_content8)
        val tmpString9 = this@PrinterOptionActivity.resources.getString(R.string.print_table_content9)


        BluetoothPrintDriver.Begin()
        BluetoothPrintDriver.ImportData(byteArrayOf(0x1d, 0x21, 0x01), 3)    //���ñ���
        BluetoothPrintDriver.ImportData(String.format(tmpString1), true)
        BluetoothPrintDriver.ImportData(String.format(tmpString2), true)
        BluetoothPrintDriver.ImportData(byteArrayOf(0x1d, 0x21, 0x01), 3)    //���ò�����
        BluetoothPrintDriver.ImportData(String.format(tmpString3), true)
        BluetoothPrintDriver.ImportData(byteArrayOf(0x1d, 0x21, 0x01), 3)    //���ñ���
        BluetoothPrintDriver.ImportData(String.format(tmpString4), true)
        BluetoothPrintDriver.ImportData(byteArrayOf(0x1d, 0x21, 0x01), 3)    //���ò�����
        BluetoothPrintDriver.ImportData(String.format(tmpString5), true)
        BluetoothPrintDriver.ImportData(byteArrayOf(0x1d, 0x21, 0x01), 3)    //���ñ���
        BluetoothPrintDriver.ImportData(String.format(tmpString6), true)
        BluetoothPrintDriver.ImportData(byteArrayOf(0x1d, 0x21, 0x01), 3)    //���ò�����
        BluetoothPrintDriver.ImportData(String.format(tmpString7), true)
        BluetoothPrintDriver.ImportData(byteArrayOf(0x1d, 0x21, 0x01), 3)    //���ñ���
        BluetoothPrintDriver.ImportData(String.format(tmpString8), true)
        BluetoothPrintDriver.ImportData(byteArrayOf(0x1d, 0x21, 0x00), 3)    //���ò�����
        BluetoothPrintDriver.ImportData(String.format(tmpString9), true)
        BluetoothPrintDriver.LF()
        BluetoothPrintDriver.LF()
        BluetoothPrintDriver.excute()
        BluetoothPrintDriver.ClearData()
    }

    //	private BloothPrinter mBloothPrinter = null;
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.printer_option)
        InitUIControl()
    }


    override fun onResume() {
        // TODO Auto-generated method stub
        //		this.mBloothPrinter = BloothPrinterActivity.mBloothPrinter;
        super.onResume()
    }


    private fun InitUIControl() {
        mBtnBack = findViewById<View>(R.id.btn_back) as Button
        mBtnBack!!.setOnClickListener(mBtnBackOnClickListener)
        mBtnPrintText = findViewById<View>(R.id.btn_print_text) as Button
        mBtnPrintText!!.setOnClickListener(mBtnPrintTextOnClickListener)
        mBtnPrintImage = findViewById<View>(R.id.btn_print_image) as Button
        mBtnPrintImage!!.setOnClickListener(mBtnPrintImageOnClickListener)
        mBtnPrint1DBarcode = findViewById<View>(R.id.btn_print_barcode) as Button
        mBtnPrint1DBarcode!!.setOnClickListener(mBtnPrint1DBarcodeOnClickListener)
        mBtnPrintTicket = findViewById<View>(R.id.btn_print_smallticket) as Button
        mBtnPrintTicket!!.setOnClickListener(mBtnPrintTicketOnClickListener)
        mBtnPrintTable = findViewById<View>(R.id.btn_print_table) as Button
        mBtnPrintTable!!.setOnClickListener(mBtnPrintTableOnClickListener)
        m1DBarcodeContent = findViewById<View>(R.id.edt_barcode_content) as EditText
    }
}
