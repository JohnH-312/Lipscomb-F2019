# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
#       Transforming a Point
#           By: Jonny Hughes
#           Last Update: 9/12/19
#           For: Dr. Crawley
#           Class: Comp Graphics
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

# Import Math to be able to use sin and cos, as well as pi
import math

# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
#               Funcitons
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

# Translate function is passed the x and y movement as well as the point vector
def translate(e, f, PointV):
    M = [[1, 0, e], [0, 1, f], [0, 0, 1]]
    return multiply(PointV, M)

# The Scale Function is passed the x and y scalars, as well as the point vector
def scale(a, b, PointV):
    M = [[a, 0, 0], [0, b, 0], [0, 0, 1]]
    return multiply(PointV, M)

# The Rotate function is passed the radian value of the rotation, as well as the poin vector
def rotate(t, PointV):
    M = [[math.cos(t), -1*math.sin(t), 0], [math.sin(t), math.cos(t), 0], [0, 0, 1]]
    return multiply(PointV, M)

# The Multiply function is passed the point vector, as well as the unique transformation matrix constructed for this certain transform
def multiply(PointV, M): #3 x 3 * 3 x 1 = 3 x 1
    ansV = [0, 0, 1]
    ansV[0] = M[0][0] * PointV[0] + M[0][1] * PointV[1] + M[0][2] * PointV[2]
    ansV[1] = M[1][0] * PointV[0] + M[1][1] * PointV[1] + M[1][2] * PointV[2]
    ansV[2] = M[2][0] * PointV[0] + M[2][1] * PointV[1] + M[2][2] * PointV[2]
    return ansV

# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
#               Beginning of Main Code Body
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

# Initialization
PointV = [0, 0, 1]        #This initializes the two arrays which contain the information about the point as we go
ansV = []

#Input of the initial x and y coords of the point
print("Input the x-coord: ", end="")
PointV[0] = float(input())
print("Input the y-coord: ", end="")
PointV[1] = float(input())

# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
#               Main While Loop
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
loop = True
while loop == True:
    print("What would you like to do Translate(t), Rotate(r), or Scale(s) or finish(f)? ", end="")
    do_this = input()
    if do_this == "t":                                      # Perform as translation transformation
        print("How much do you want to move in the x-axis:", end="")
        e = float(input())
        print("How much do you want to move in the y-axis:", end="")
        f = float(input())
        ansV = translate(e, f, PointV)
    elif do_this == "r":                                    # Perform as rotation transformation
        print("How much do you want to rotate in Degrees:", end="")
        c = float(input())
        t = float(c) * (float(math.pi) / 180.0)
        ansV = rotate(t, PointV)
    elif do_this == "s":                                    # Perform as scaling transformation
        print("How much do you want to scale in the x-axis:", end="")
        a = float(input())
        print("How much do you want to scale in the y-axis:", end="")
        b = float(input())
        ansV = scale(a, b, PointV)
    elif do_this == "f":                                    # Ends the While Loop, thus terminating the program after the final output
        loop = False
    PointV = ansV

    #Output
    print("The new location is x:{:.2f} and y:{:.2f}".format(ansV[0], ansV[1]))